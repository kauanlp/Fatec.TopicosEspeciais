package br.com.fatecmogidascruzes.topicosbackend1;

public class LocalizadorServico {

    public static ContatoDAO getContatoDAO() {
        return new ContatoDAOPostgreSQL();
    }

}
