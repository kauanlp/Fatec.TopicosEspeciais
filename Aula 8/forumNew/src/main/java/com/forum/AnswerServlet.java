package com.forum;

import com.forum.DAO.AnswerDAO;
import com.forum.DAO.FactoryDAO;
import com.forum.DAO.QuestionDAO;
import com.forum.domain.Answer;
import com.forum.domain.Question;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;

public class AnswerServlet extends HttpServlet {
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        AnswerDAO answerDAO = FactoryDAO.getAnswerDAO();

        if (action == null) {
            try {
                request.setAttribute("answers", answerDAO.findAll());
                request.getRequestDispatcher("/listAnswers.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("save")) {
            try {
                String description = request.getParameter("description");
                Answer answer = new Answer(description);
                answerDAO.save(answer);

                request.getRequestDispatcher("/sucessSaveAnswer.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/failSaveAnswer.jsp").forward(request, response);
            }
        }
    }
}
