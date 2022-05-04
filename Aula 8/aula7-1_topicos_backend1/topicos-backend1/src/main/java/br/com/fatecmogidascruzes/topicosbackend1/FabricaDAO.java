package br.com.fatecmogidascruzes.topicosbackend1;

public class FabricaDAO {

    public static ContatoDAO getContatoDAO(/*String arquivoConfiguracao*/) {

        // Tenta criar conexão com o PostgreSQL. Deu? retorna. Não deu, vai para o próximo
        // Tenta criar conexão com o Oracle. Deu? retorna. Não deu, vai para o próximo
        // Tenta criar conexão com o MySQL. Deu? retorna. Não deu, vai para o próximo

        return new ContatoDAOPostgreSQL();
    }

}
