package br.com.fatecmogidascruzes.topicosbackend1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "gestaoContato", value = "/contatos")
public class GestaoContato extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            Class.forName("org.postgresql.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/db", "postgres", "root");

            PreparedStatement sql = conexao.prepareStatement("SELECT id, nome, telefone, email FROM _contatos");
            ResultSet resultado = sql.executeQuery();
            List<Contato> contatos = new ArrayList<>();
            while (resultado.next()) {
                Contato contato = new Contato(resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("telefone"),
                        resultado.getString("email"));
                contatos.add(contato);
            }

            request.setAttribute("contatos", contatos);
            request.getRequestDispatcher("/index.jsp").forward(request, response);

            conexao.close();
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}