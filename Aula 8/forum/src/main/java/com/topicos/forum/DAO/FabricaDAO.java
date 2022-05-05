package com.topicos.forum.DAO;

public class FabricaDAO {
    public static PerguntaDAO getPerguntaDAO(/*String arquivoConfiguracao*/) {

        // Tenta criar conexão com o PostgreSQL. Deu? retorna. Não deu, vai para o próximo
        // Tenta criar conexão com o Oracle. Deu? retorna. Não deu, vai para o próximo
        // Tenta criar conexão com o MySQL. Deu? retorna. Não deu, vai para o próximo

        return new PerguntaDAOPostgreSQL();
    }
}
