package br.com.fatecmogidascruzes.topicosbackend1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class GestaoContato extends HttpServlet {

    // Injecao de dependencia
    //private ContatoDAO contatoDAO;

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String acao = request.getParameter("acao");

        // Service Locator (Martin Fowler): https://martinfowler.com/articles/injection.html
        // ContatoDAO contatoDAO = LocalizadorServico.getContatoDAO();

        // Fabrica
        ContatoDAO contatoDAO = FabricaDAO.getContatoDAO();

        if (acao == null) {
            try {
                request.setAttribute("contatos", contatoDAO.listarTodos());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (acao.equalsIgnoreCase("inserir")) {
            try {
                String nome = request.getParameter("nome");
                String telefone = request.getParameter("telefone");
                String email = request.getParameter("email");

                Contato contato = new Contato(nome, telefone, email);
                contatoDAO.salvar(contato);

                request.getRequestDispatcher("/sucessoInsercao.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/falhaInsercao.jsp").forward(request, response);
            }
        } else if (request.getParameter("alterar") != null) {
            try {
                String idStr = request.getParameter("alterar");
                int id = Integer.valueOf(idStr);

                request.setAttribute("contato", contatoDAO.consultarPorId(id));
                request.getRequestDispatcher("/alterar.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/alterar.jsp").forward(request, response);
            }
        } else if (acao.equalsIgnoreCase("atualizar")) {
            try {
                String idStr = request.getParameter("id");
                int id = Integer.valueOf(idStr);
                String nome = request.getParameter("nome");
                String telefone = request.getParameter("telefone");
                String email = request.getParameter("email");

                Contato contato = new Contato(id, nome, telefone, email);
                contatoDAO.atualizar(contato);

                request.getRequestDispatcher("/sucessoAtualizacao.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/falhaAtualizacao.jsp").forward(request, response);
            }
        } else if (request.getParameter("excluir") != null) {
            try {
                String idStr = request.getParameter("excluir");
                int id = Integer.valueOf(idStr);

                request.setAttribute("contato", contatoDAO.consultarPorId(id));
                request.getRequestDispatcher("/confirmarExclusao.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/confirmarExclusao.jsp").forward(request, response);
            }
        } else if (acao.equalsIgnoreCase("confirmarExclusao")) {
            try {
                String idStr = request.getParameter("id");
                int id = Integer.valueOf(idStr);

                Contato contato = new Contato(id);
                contatoDAO.excluir(contato);

                request.getRequestDispatcher("/sucessoExclusao.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/falhaExclusao.jsp").forward(request, response);
            }
        }

    }



}