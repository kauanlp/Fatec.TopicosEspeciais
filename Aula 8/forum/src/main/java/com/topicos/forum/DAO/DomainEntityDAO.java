package com.topicos.forum.DAO;

import com.topicos.forum.DomainEntity;
import com.topicos.forum.Pergunta;

import java.sql.SQLException;
import java.util.List;

public interface DomainEntityDAO {

    void salvar(DomainEntity entity) throws ClassNotFoundException, SQLException;

    void atualizar(DomainEntity entity) throws ClassNotFoundException, SQLException;

    void excluir(int id) throws ClassNotFoundException, SQLException;

    void excluir(DomainEntity entity) throws ClassNotFoundException, SQLException;

    List<DomainEntity> listarTodos() throws ClassNotFoundException, SQLException;

    Pergunta consultarPorId(int id) throws ClassNotFoundException, SQLException;

}
