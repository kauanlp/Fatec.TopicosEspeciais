package com.topicos.forum.DAO;

import com.topicos.forum.dominio.DomainEntity;
import com.topicos.forum.persistencia.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DomainEntityDAO implements IDAO {

    protected Connection connection;
    protected String table;
    protected String idTable;
    protected boolean ctrlTransaction=true;

    public DomainEntityDAO(Connection connection, String table, String idTable) {
        this.table = table;
        this.idTable = idTable;
        this.connection = connection;
    }

    protected DomainEntityDAO(String table, String idTable) {
        this.table = table;
        this.idTable = idTable;
    }

    public void excluir(DomainEntity entidade) {
        openConnection();
        PreparedStatement pst=null;
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(table);
        sb.append(" WHERE ");
        sb.append(idTable);
        sb.append("=");
        sb.append("?");
        try {
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sb.toString());
            pst.setInt(1, entidade.getId());

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

            try {
                pst.close();
                if(ctrlTransaction)
                    connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void openConnection() {
        try {

            if(connection == null || connection.isClosed())
                connection = Database.getConnection();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
