package com.topicos.forum;

<<<<<<< Updated upstream
import com.topicos.forum.DAO.FabricaDAO;
import com.topicos.forum.DAO.PerguntaDAO;
import com.topicos.forum.dominio.Pergunta;
import com.topicos.forum.dominio.Resposta;
=======
import com.topicos.forum.DAO.PerguntaDAO;
>>>>>>> Stashed changes

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
<<<<<<< Updated upstream
import java.util.Date;
import java.util.List;
=======
>>>>>>> Stashed changes

public class GestaoPergunta extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String acao = request.getParameter("acao");

<<<<<<< Updated upstream
        PerguntaDAO perguntaDAO = FabricaDAO.getPerguntaDAO();

        if (acao == null) {
            try {
                request.setAttribute("perguntas", perguntaDAO.listarTodos());
=======
        // Service Locator (Martin Fowler): https://martinfowler.com/articles/injection.html
        // PerguntaDAO perguntaDAO = LocalizadorServico.getPerguntaDAO();

        // Fabrica
        PerguntaDAO perguntaDAO = new PerguntaDAO();

        if (acao == null) {
            try {
                request.setAttribute("contatos", perguntaDAO.listarTodos());
>>>>>>> Stashed changes
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (acao.equalsIgnoreCase("inserir")) {
            try {
<<<<<<< Updated upstream
                String titulo = request.getParameter("titulo");
                String descricao = request.getParameter("descricao");
                Date dtCadastro = new Date();

                Pergunta pergunta = new Pergunta(titulo, descricao, dtCadastro);
                perguntaDAO.salvar(pergunta);
=======
                String nome = request.getParameter("nome");
                String telefone = request.getParameter("telefone");
                String email = request.getParameter("email");

                Contato contato = new Contato(nome, telefone, email);
                perguntaDAO.salvar(contato);
>>>>>>> Stashed changes

                request.getRequestDispatcher("/sucessoInsercao.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/falhaInsercao.jsp").forward(request, response);
            }
        } else if (request.getParameter("alterar") != null) {
            try {
                String idStr = request.getParameter("alterar");
                int id = Integer.valueOf(idStr);

<<<<<<< Updated upstream
                request.setAttribute("pergunta", perguntaDAO.consultarPorId(id));
=======
                request.setAttribute("contato", perguntaDAO.consultarPorId(id));
>>>>>>> Stashed changes
                request.getRequestDispatcher("/alterar.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/alterar.jsp").forward(request, response);
            }
        } else if (acao.equalsIgnoreCase("atualizar")) {
            try {
                String idStr = request.getParameter("id");
                int id = Integer.valueOf(idStr);
<<<<<<< Updated upstream
                String titulo = request.getParameter("titulo");
                String descricao = request.getParameter("descricao");
                Date dtCadastro = new Date();
                Pergunta pergunta = new Pergunta(id, titulo, descricao, dtCadastro);
                perguntaDAO.atualizar(pergunta);
=======
                String nome = request.getParameter("nome");
                String telefone = request.getParameter("telefone");
                String email = request.getParameter("email");

                Contato contato = new Contato(id, nome, telefone, email);
                perguntaDAO.atualizar(contato);
>>>>>>> Stashed changes

                request.getRequestDispatcher("/sucessoAtualizacao.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/falhaAtualizacao.jsp").forward(request, response);
            }
        } else if (request.getParameter("excluir") != null) {
            try {
                String idStr = request.getParameter("excluir");
                int id = Integer.valueOf(idStr);

<<<<<<< Updated upstream
                request.setAttribute("pergunta", perguntaDAO.consultarPorId(id));
=======
                request.setAttribute("contato", perguntaDAO.consultarPorId(id));
>>>>>>> Stashed changes
                request.getRequestDispatcher("/confirmarExclusao.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/confirmarExclusao.jsp").forward(request, response);
            }
        } else if (acao.equalsIgnoreCase("confirmarExclusao")) {
            try {
                String idStr = request.getParameter("id");
                int id = Integer.valueOf(idStr);
<<<<<<< Updated upstream
                Pergunta pergunta = new Pergunta(id);
                perguntaDAO.excluir(pergunta);
=======

                Contato contato = new Contato(id);
                perguntaDAO.excluir(contato);
>>>>>>> Stashed changes

                request.getRequestDispatcher("/sucessoExclusao.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/falhaExclusao.jsp").forward(request, response);
            }
        }
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    }
}
