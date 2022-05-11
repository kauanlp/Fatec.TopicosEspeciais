package com.forum;

import com.forum.DAO.AnswerDAO;
import com.forum.DAO.FactoryDAO;
import com.forum.DAO.QuestionDAO;
import com.forum.domain.Question;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;

public class QuestionServlet extends HttpServlet {
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        QuestionDAO questionDAO = FactoryDAO.getQuestionDAO();
        AnswerDAO answerDAO = FactoryDAO.getAnswerDAO();

        if (action == null) {
            try {
                request.setAttribute("questions", questionDAO.findAll());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("save")) {
            try {
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                String tag = request.getParameter("tag");
                Question question = new Question(title, description, tag);
                questionDAO.save(question);

                request.getRequestDispatcher("/sucessSaveQuestion.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/failSaveQuestion.jsp").forward(request, response);
            }
        } else if (action.equalsIgnoreCase("findOne")) {
            try {
                String idStr = request.getParameter("questionId");
                int id = Integer.valueOf(idStr);
                request.setAttribute("question", questionDAO.findOne(id));
                request.setAttribute("answers", answerDAO.findAll());
                request.getRequestDispatcher("/listAnswers.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/listAnswers.jsp").forward(request, response);
            }
        }
    }
}
