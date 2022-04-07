package br.com.fatecmogidascruzes.avaliacao.DAO;

import br.com.fatecmogidascruzes.avaliacao.dominio.Produto;
import br.com.fatecmogidascruzes.avaliacao.persistencia.BancoDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAOPostgreSQL implements ProdutoDAO{
    @Override
    public void salvar(Produto produto) throws ClassNotFoundException, SQLException {
        try (Connection conexao = BancoDados.getConexao()) {
            PreparedStatement sql = conexao.prepareStatement("INSERT INTO _produtos(nome, descricao, precoUnitario, linkFoto) VALUES(?, ?, ?, ?)");
            sql.setString(1, produto.getNome());
            sql.setString(2, produto.getDescricao());
            sql.setDouble(3, produto.getPrecoUnitario());
            sql.setString(4, produto.getLinkFoto());
            sql.executeUpdate();
        }
    }

    @Override
    public void atualizar(Produto produto) throws ClassNotFoundException, SQLException {
        try (Connection conexao = BancoDados.getConexao()) {
            PreparedStatement sql = conexao.prepareStatement("UPDATE _produtos SET nome=?, descricao=?, precoUnitario=?, linkFoto=? WHERE id=?");
            sql.setString(1, produto.getNome());
            sql.setString(2, produto.getDescricao());
            sql.setDouble(3, produto.getPrecoUnitario());
            sql.setString(4, produto.getLinkFoto());
            sql.setInt(5, produto.getId());
            sql.executeUpdate();
        }
    }

    @Override
    public void excluir(int id) throws ClassNotFoundException, SQLException {
        try (Connection conexao = BancoDados.getConexao()) {
            PreparedStatement sql = conexao.prepareStatement("DELETE FROM _produtos WHERE id=?");
            sql.setInt(1, id);
            sql.executeUpdate();
        }
    }

    @Override
    public void excluir(Produto produto) throws ClassNotFoundException, SQLException {
        excluir(produto.getId());
    }

    @Override
    public List<Produto> listarTodos() throws ClassNotFoundException, SQLException {
        try (Connection conexao = BancoDados.getConexao()) {
            PreparedStatement sql = conexao.prepareStatement("SELECT id, nome, descricao, precoUnitario, linkFoto FROM _produtos");
            ResultSet resultado = sql.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while (resultado.next()) {
                Produto produto = new Produto(resultado.getInt("id"),
                resultado.getString("nome"),
                resultado.getString("descricao"),
                resultado.getDouble("precoUnitario"),
                resultado.getString("linkFoto"));
                produtos.add(produto);
            }
            return produtos;
        }
    }

    @Override
    public Produto consultarPorId(int id) throws ClassNotFoundException, SQLException {
        try (Connection conexao = BancoDados.getConexao()) {
            PreparedStatement sql = conexao.prepareStatement("SELECT id, nome, descricao, precoUnitario, linkFoto FROM _produtos WHERE id=?");
            sql.setInt(1, id);

            Produto produto = null;
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                produto = new Produto(resultado.getInt("id"), resultado.getString("nome"), resultado.getString("descricao"), resultado.getDouble("precoUnitario"), resultado.getString("linkFoto"));
            }
            return produto;
        }
    }

}
