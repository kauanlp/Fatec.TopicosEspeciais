package br.com.fatecmogidascruzes.topicosbackend1;

import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) throws Exception {

        Contato c = new Contato("João da Silva", "11-23456789", "joao@fatec.sp.gov.br");
        DAOGenerico daoGenerico = new DAOGenerico();
        daoGenerico.salvar(c);

        Compromisso c1 = new Compromisso(LocalDate.now(), "Prova Topicos", true);
        daoGenerico.salvar(c1);

        Convert.toInt(10.5);
        Convert.toInt(10.5f);
        Convert.toInt(true);
        Convert.toString(10.5);
        Convert.toString(10);
        Convert.toString(LocalDate.now());

    }

}
