package br.com.fatecmogidascruzes.avaliacao;

import br.com.fatecmogidascruzes.avaliacao.DAO.FabricaDAO;
import br.com.fatecmogidascruzes.avaliacao.DAO.ProdutoDAO;
import br.com.fatecmogidascruzes.avaliacao.dominio.Produto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class GestaoProduto extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String acao = request.getParameter("acao");

        ProdutoDAO produtoDAO = FabricaDAO.getProdutoDAO();

        if (acao == null) {
            try {
                request.setAttribute("produtos", produtoDAO.listarTodos());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (acao.equalsIgnoreCase("inserir")) {
            try {
                String nome = request.getParameter("nome");
                String descricao = request.getParameter("descricao");
                Double precoUnitario = Double.valueOf(request.getParameter("precoUnitario"));
                String linkFoto = request.getParameter("linkFoto");

                if(nome.isEmpty()){
                    throw new SQLException("Nome é um campo obrigatório");
                }

                if(precoUnitario.isNaN()){
                    throw new SQLException("Preço Unitário é um campo obrigatório");
                }

                if(linkFoto.isEmpty()){
                    throw new SQLException("Link da foto é um campo obrigatório");
                }

                if(nome.length() > 100){
                    throw new SQLException("Nome maior que o permitido");
                }

                if(descricao.length() > 500){
                    throw new SQLException("Descrição maior que o permitido");
                }

                if(linkFoto.length() > 100){
                    throw new SQLException("Link da foto maior que o permitido");
                }

                Produto produto = new Produto(nome, descricao, precoUnitario, linkFoto);
                produtoDAO.salvar(produto);

                request.getRequestDispatcher("/sucessoInsercao.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/falhaInsercao.jsp").forward(request, response);
            }
        } else if (request.getParameter("alterar") != null) {
            try {
                String idStr = request.getParameter("alterar");
                int id = Integer.valueOf(idStr);

                request.setAttribute("produto", produtoDAO.consultarPorId(id));
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
                String descricao = request.getParameter("descricao");
                Double precoUnitario = Double.valueOf(request.getParameter("precoUnitario"));
                String linkFoto = request.getParameter("linkFoto");

                if(nome.isEmpty()){
                    throw new SQLException("Nome é um campo obrigatório");
                }

                if(precoUnitario.isNaN()){
                    throw new SQLException("Preço Unitário é um campo obrigatório");
                }

                if(linkFoto.isEmpty()){
                    throw new SQLException("Link da foto é um campo obrigatório");
                }

                if(nome.length() > 100){
                    throw new SQLException("Nome maior que o permitido");
                }

                if(descricao.length() > 500){
                    throw new SQLException("Descrição maior que o permitido");
                }

                if(linkFoto.length() > 100){
                    throw new SQLException("Link da foto maior que o permitido");
                }

                Produto produto = new Produto(id, nome, descricao, precoUnitario, linkFoto);
                produtoDAO.atualizar(produto);

                request.getRequestDispatcher("/sucessoAtualizacao.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/falhaAtualizacao.jsp").forward(request, response);
            }
        } else if (request.getParameter("excluir") != null) {
            try {
                String idStr = request.getParameter("excluir");
                int id = Integer.valueOf(idStr);

                request.setAttribute("produto", produtoDAO.consultarPorId(id));
                request.getRequestDispatcher("/confirmarExclusao.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/confirmarExclusao.jsp").forward(request, response);
            }
        } else if (acao.equalsIgnoreCase("confirmarExclusao")) {
            try {
                String idStr = request.getParameter("id");
                int id = Integer.valueOf(idStr);

                Produto produto = new Produto(id);
                produtoDAO.excluir(produto);

                request.getRequestDispatcher("/sucessoExclusao.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("/falhaExclusao.jsp").forward(request, response);
            }
        }

    }



}