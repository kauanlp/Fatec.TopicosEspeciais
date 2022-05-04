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

@WebServlet(name = "inserirContato", value = "/inserirContato")
public class InserirContato extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            String nome = request.getParameter("nome");
//                if(nome == null || nome.length() < 4) {
//
//                }
            Class.forName("org.postgresql.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/db", "postgres", "root");

            PreparedStatement sql = conexao.prepareStatement("INSERT INTO _contatos(nome, telefone, email) VALUES(?, ?, ?)");
            sql.setString(1, nome);
            sql.setString(2, request.getParameter("telefone"));
            sql.setString(3, request.getParameter("email"));
            sql.executeUpdate();
            conexao.close();

            request.setAttribute("contatoInseridoComSucesso", "true");
            request.getRequestDispatcher("/realizarInsercao.jsp").forward(request, response);

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}