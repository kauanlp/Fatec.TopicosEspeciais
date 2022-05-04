package br.com.fatecmogidascruzes.topicosbackend1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "excluirContato", value = "/excluirContato")
public class ExcluirContato extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            String idStr = request.getParameter("id");
            int id = Integer.valueOf(idStr);

            Class.forName("org.postgresql.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/db", "postgres", "root");

            PreparedStatement sql = conexao.prepareStatement("DELETE FROM _contatos WHERE id=?");
            sql.setInt(1, id);
            sql.executeUpdate();
            conexao.close();

            request.setAttribute("contatoExcluidoComSucesso", "true");
            request.getRequestDispatcher("/realizarExclusao.jsp").forward(request, response);

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}