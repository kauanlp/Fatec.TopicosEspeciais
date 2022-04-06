package br.com.fatecmogidascruzes.topicosbackend1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "buscarContato", value = "/buscarContato")
public class BuscarContato extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            Contato contato = null;
            String idStr = request.getParameter("id");
            int id = Integer.valueOf(idStr);

            Class.forName("org.postgresql.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/db", "postgres", "root");
            PreparedStatement sql = conexao.prepareStatement("SELECT id, nome, telefone, email FROM _contatos WHERE id=?");
            sql.setInt(1, id);

            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                contato = new Contato(resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("telefone"),
                        resultado.getString("email"));
            } else {
                request.setAttribute("erro", "Nenhum contato com este identificador foi encontrado");
            }
            conexao.close();

            request.setAttribute("contato", contato);

            String operacao = (String) request.getAttribute("operacao");
            request.getRequestDispatcher(operacao).forward(request, response);

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}