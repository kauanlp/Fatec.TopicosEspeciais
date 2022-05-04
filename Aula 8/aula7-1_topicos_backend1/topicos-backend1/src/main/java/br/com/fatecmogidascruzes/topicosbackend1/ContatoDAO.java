package br.com.fatecmogidascruzes.topicosbackend1;

import java.sql.SQLException;
import java.util.List;

public interface ContatoDAO {

    void salvar(Contato contato) throws ClassNotFoundException, SQLException;

    void atualizar(Contato contato) throws ClassNotFoundException, SQLException;

    void excluir(int id) throws ClassNotFoundException, SQLException;

    void excluir(Contato contato) throws ClassNotFoundException, SQLException;

    List<Contato> listarTodos() throws ClassNotFoundException, SQLException;

    Contato consultarPorId(int id) throws ClassNotFoundException, SQLException;

}
