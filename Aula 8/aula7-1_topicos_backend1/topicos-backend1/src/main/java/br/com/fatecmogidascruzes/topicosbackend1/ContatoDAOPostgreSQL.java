package br.com.fatecmogidascruzes.topicosbackend1;

import br.com.fatecmogidascruzes.topicosbackend1.persistencia.BancoDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAOPostgreSQL implements ContatoDAO {

    @Override
    public void salvar(Contato contato) throws ClassNotFoundException, SQLException {
        try (Connection conexao = BancoDados.getConexao()) {
            PreparedStatement sql = conexao.prepareStatement("INSERT INTO _contatos(nome, telefone, email) VALUES(?, ?, ?)");
            sql.setString(1, contato.getNome());
            sql.setString(2, contato.getTelefone());
            sql.setString(3, contato.getEmail());
            sql.executeUpdate();
        }
    }

    @Override
    public void atualizar(Contato contato) throws ClassNotFoundException, SQLException {
        try (Connection conexao = BancoDados.getConexao()) {
            PreparedStatement sql = conexao.prepareStatement("UPDATE _contatos SET nome=?, telefone=?, email=? WHERE id=?");
            sql.setString(1, contato.getNome());
            sql.setString(2, contato.getTelefone());
            sql.setString(3, contato.getEmail());
            sql.setInt(4, contato.getCodigo());
            sql.executeUpdate();
        }
    }

    @Override
    public void excluir(int id) throws ClassNotFoundException, SQLException {
        try (Connection conexao = BancoDados.getConexao()) {
            PreparedStatement sql = conexao.prepareStatement("DELETE FROM _contatos WHERE id=?");
            sql.setInt(1, id);
            sql.executeUpdate();
        }
    }

    @Override
    public void excluir(Contato contato) throws ClassNotFoundException, SQLException {
        excluir(contato.getCodigo());
    }

    @Override
    public List<Contato> listarTodos() throws ClassNotFoundException, SQLException {
        try (Connection conexao = BancoDados.getConexao()) {
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
            return contatos;
        }
    }

    @Override
    public Contato consultarPorId(int id) throws ClassNotFoundException, SQLException {
        try (Connection conexao = BancoDados.getConexao()) {
            PreparedStatement sql = conexao.prepareStatement("SELECT id, nome, telefone, email FROM _contatos WHERE id=?");
            sql.setInt(1, id);

            Contato contato = null;
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                contato = new Contato(resultado.getInt("id"), resultado.getString("nome"), resultado.getString("telefone"), resultado.getString("email"));
            }
            return contato;
        }
    }

}
