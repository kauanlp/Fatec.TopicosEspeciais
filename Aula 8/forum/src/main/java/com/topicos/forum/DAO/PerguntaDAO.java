package com.topicos.forum.DAO;

import com.topicos.forum.dominio.DomainEntity;
import com.topicos.forum.dominio.Pergunta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerguntaDAO extends DomainEntityDAO{
    public PerguntaDAO(Connection cx){
        super(cx, "tb_perguntas", "per_id");
    }

    public PerguntaDAO(){
        super("tb_perguntas", "per_id");
    }

    public void salvar(DomainEntity entidade) {
        Pergunta pergunta = (Pergunta) entidade;
        openConnection();
        PreparedStatement pst=null;
        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO tb_perguntas(per_descricao, per_dt_cadastro) ");
        sql.append(" VALUES (?, ?)");
        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(),
                    Statement.RETURN_GENERATED_KEYS);

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
        Pergunta pergunta = (Pergunta) entidade;
        List<DomainEntity> listaPerguntas = new ArrayList<DomainEntity>();
        openConnection();
        PreparedStatement pst=null;
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM ");
        sql.append(table);
        sql.append(" WHERE ");
        sql.append(idTable);
        sql.append("=");
        sql.append("?");

        try {
            pst = connection.prepareStatement(sql.toString());
            pst.setInt(1, pergunta.getId());

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                listaPerguntas.add(new Pergunta(
                        rs.getInt("per_id"),
                        rs.getString("per_descricao"),
                        new Date(rs.getTimestamp("per_dt_cadastro").getTime())
                ));
            }
        } catch (SQLException e) {
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
        return listaPerguntas;
    }

}
