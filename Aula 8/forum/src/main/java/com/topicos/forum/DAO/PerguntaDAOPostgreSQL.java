package com.topicos.forum.DAO;

import com.topicos.forum.dominio.Pergunta;
import com.topicos.forum.persistencia.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerguntaDAOPostgreSQL implements PerguntaDAO {

    @Override
    public void salvar(Pergunta pergunta) throws ClassNotFoundException, SQLException {
        try (Connection conexao = Database.getConnection()) {
            PreparedStatement sql = conexao.prepareStatement("INSERT INTO _perguntas(per_titulo, per_descricao, per_DtCadastro) VALUES(?,?,?)");
            sql.setString(1, pergunta.getTitulo());
            sql.setString(2, pergunta.getDescricao());
            sql.setDate(3, (Date) pergunta.getDtCadastro());
            sql.executeUpdate();
        }
    }

    @Override
    public void atualizar(Pergunta pergunta) throws ClassNotFoundException, SQLException {
        try (Connection conexao = Database.getConnection()) {
            PreparedStatement sql = conexao.prepareStatement("UPDATE _perguntas SET per_titulo=?, per_descricao=?, per_dtCadastro=? WHERE id=?");
            sql.setString(1, pergunta.getTitulo());
            sql.setString(2, pergunta.getDescricao());
            sql.setDate(3, (Date) pergunta.getDtCadastro());
            sql.setInt(3, pergunta.getId());
            sql.executeUpdate();
        }
    }

    @Override
    public void excluir(int id) throws ClassNotFoundException, SQLException {
        try (Connection conexao = Database.getConnection()) {
            PreparedStatement sql = conexao.prepareStatement("DELETE FROM _perguntas WHERE id=?");
            sql.setInt(1, id);
            sql.executeUpdate();
        }
    }

    @Override
    public void excluir(Pergunta pergunta) throws ClassNotFoundException, SQLException {
        excluir(pergunta.getId());
    }

    @Override
    public List<Pergunta> listarTodos() throws ClassNotFoundException, SQLException {
        try (Connection conexao = Database.getConnection()) {
            PreparedStatement sql = conexao.prepareStatement("SELECT id, per_titulo, per_descricao, per_dtCadastro FROM _perguntas");
            ResultSet resultado = sql.executeQuery();
            List<Pergunta> perguntas = new ArrayList<>();
            while (resultado.next()) {
                Pergunta pergunta = new Pergunta(resultado.getInt("id"),
                        resultado.getString("per_titulo"),
                        resultado.getString("per_descricao"),
                        resultado.getDate("per_dtCadastro"));
                        perguntas.add(pergunta);
            }
            return perguntas;
        }
    }

    @Override
    public Pergunta consultarPorId(int id) throws ClassNotFoundException, SQLException {
        try (Connection conexao = Database.getConnection()) {
            PreparedStatement sql = conexao.prepareStatement("SELECT per_titulo, per_descricao, per_dtCadastro FROM _perguntas WHERE id=?");
            sql.setInt(1, id);

            Pergunta pergunta = null;
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                pergunta = new Pergunta(resultado.getInt("id"), resultado.getString("per_titulo"), resultado.getString("per_descricao"), resultado.getDate("per_dtCadastro"));
            }
            return pergunta;
        }
    }

}
