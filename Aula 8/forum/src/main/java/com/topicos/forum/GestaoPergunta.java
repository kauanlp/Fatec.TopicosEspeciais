package com.topicos.forum;

import com.topicos.forum.DAO.FabricaDAO;
import com.topicos.forum.DAO.PerguntaDAO;
import com.topicos.forum.dominio.Pergunta;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class GestaoPergunta extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String acao = request.getParameter("acao");

        PerguntaDAO perguntaDAO = FabricaDAO.getPerguntaDAO();

        if (acao == null) {
            try {
                request.setAttribute("perguntas", perguntaDAO.listarTodos());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (acao.equalsIgnoreCase("inserir")) {
            try {
                String titulo = request.getParameter("titulo");
                String descricao = request.getParameter("descricao");

                Pergunta pergunta = new Pergunta(descricao);
                perguntaDAO.salvar(pergunta);

                request.getRequestDispatcher("/sucessoInsercao.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/falhaInsercao.jsp").forward(request, response);
            }
        } else if (request.getParameter("alterar") != null) {
            try {
                String idStr = request.getParameter("alterar");
                int id = Integer.valueOf(idStr);

                request.setAttribute("pergunta", perguntaDAO.consultarPorId(id));
                request.getRequestDispatcher("/alterar.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/alterar.jsp").forward(request, response);
            }
        } else if (acao.equalsIgnoreCase("atualizar")) {
            try {
                String idStr = request.getParameter("id");
                int id = Integer.valueOf(idStr);
                String titulo = request.getParameter("titulo");
                String descricao = request.getParameter("descricao");

                Pergunta pergunta = new Pergunta(id, descricao);
                perguntaDAO.atualizar(pergunta);

                request.getRequestDispatcher("/sucessoAtualizacao.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/falhaAtualizacao.jsp").forward(request, response);
            }
        } else if (request.getParameter("excluir") != null) {
            try {
                String idStr = request.getParameter("excluir");
                int id = Integer.valueOf(idStr);

                request.setAttribute("pergunta", perguntaDAO.consultarPorId(id));
                request.getRequestDispatcher("/confirmarExclusao.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/confirmarExclusao.jsp").forward(request, response);
            }
        } else if (acao.equalsIgnoreCase("confirmarExclusao")) {
            try {
                String idStr = request.getParameter("id");
                int id = Integer.valueOf(idStr);
                Pergunta pergunta = new Pergunta(id);
                perguntaDAO.excluir(pergunta);

                request.getRequestDispatcher("/sucessoExclusao.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/falhaExclusao.jsp").forward(request, response);
            }
        }
    }
}
