package com.topicos.forum.DAO;

import com.topicos.forum.dominio.Pergunta;
import com.topicos.forum.persistencia.Database;

import java.sql.SQLException;
import java.util.List;

public interface PerguntaDAO {

    void salvar(Pergunta pergunta) throws ClassNotFoundException, SQLException;

    void atualizar(Pergunta pergunta) throws ClassNotFoundException, SQLException;

    void excluir(int id) throws ClassNotFoundException, SQLException;

    void excluir(Pergunta pergunta) throws ClassNotFoundException, SQLException;

<<<<<<< Updated upstream
    List<Pergunta> listarTodos() throws ClassNotFoundException, SQLException;
=======
            pst.setString(1, pergunta.getDescricao());
            pst.setTimestamp(2, new Timestamp(pergunta.getDtCadastro().getTime()));

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            if(rs.next()){
                pergunta.setId(rs.getInt(1));
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            if(ctrlTransaction){
                try {
                    pst.close();
                    if(ctrlTransaction)
                        connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void alterar(DomainEntity entidade) {
        Pergunta pergunta = (Pergunta) entidade;
        openConnection();
        PreparedStatement pst=null;
        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE tb_perguntas SET per_descricao=? ");
        sql.append("WHERE cnae_id=?;");
        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(),
                    Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, pergunta.getDescricao());
            pst.setInt(2, pergunta.getId());

            pst.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            if(ctrlTransaction){
                try {
                    pst.close();
                    if(ctrlTransaction)
                        connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<DomainEntity> consultar(DomainEntity entidade) {
        try (Connection conexao = Database.getConnection()) {
            PreparedStatement sql = conexao.prepareStatement("SELECT id, nome, telefone, email FROM _contatos");
            ResultSet resultado = sql.executeQuery();
            List<DomainEntity> perguntas = new ArrayList<DomainEntity>();
            while (resultado.next()) {
                Pergunta perguntas = new Pergunta(
                        resultado.getInt("id"),
                        resultado.getString("descricao"));
                perguntas.add(pergunta);
            }
        return perguntas;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Pergunta consultarPorId(int id) throws ClassNotFoundException, SQLException {
        try (Connection conexao = Database.getConnection()) {
            PreparedStatement sql = conexao.prepareStatement("SELECT id, descricao FROM _perguntas WHERE id=?");
            sql.setInt(1, id);

            Pergunta pergunta = null;
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                pergunta = new Pergunta(resultado.getInt("id"), resultado.getString("descricao"));
            }
            return pergunta;
        }
    }
>>>>>>> Stashed changes

    Pergunta consultarPorId(int id) throws ClassNotFoundException, SQLException;
}
