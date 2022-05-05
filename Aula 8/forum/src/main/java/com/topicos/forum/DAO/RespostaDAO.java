package com.topicos.forum.DAO;

import com.topicos.forum.dominio.Pergunta;

import java.sql.SQLException;
import java.util.List;

public interface RespostaDAO {

    void salvar(Pergunta pergunta) throws ClassNotFoundException, SQLException;

    void atualizar(Pergunta pergunta) throws ClassNotFoundException, SQLException;

    void excluir(int id) throws ClassNotFoundException, SQLException;

    void excluir(Pergunta pergunta) throws ClassNotFoundException, SQLException;

    List<Pergunta> listarTodos() throws ClassNotFoundException, SQLException;

    Pergunta consultarPorId(int id) throws ClassNotFoundException, SQLException;
}
