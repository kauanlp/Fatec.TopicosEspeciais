package br.com.fatecmogidascruzes.topicosbackend1;

import br.com.fatecmogidascruzes.topicosbackend1.persistencia.anotacoes.Id;

import java.time.LocalDate;

public class Compromisso {

    @Id
    private int id;
    private LocalDate data;
    private String descricao;
    private boolean confirmado;

    public Compromisso(LocalDate data, String descricao, boolean confirmado) {
        this.data = data;
        this.descricao = descricao;
        this.confirmado = confirmado;
    }
}
