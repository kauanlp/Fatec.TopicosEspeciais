package br.com.fatecmogidascruzes.topicosbackend1.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDados {

    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conexao = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/db", "postgres", "root");
        return conexao;
    }

}
