package com.topicos.forum.dominio;

import com.topicos.forum.persistencia.anotacoes.Coluna;
import com.topicos.forum.persistencia.anotacoes.Tabela;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Tabela(nome = "perguntas")
public class Pergunta extends DomainEntity {
    @Coluna(nome = "titulo")
    private String titulo;

    @Coluna(nome = "descricao")
    private String descricao;

    private List<Resposta> respostas;

    public Pergunta(int id, String descricao, Date dtCadastro) {
        super(id, dtCadastro);
        this.descricao = descricao;
    }

    public Pergunta(int id, String descricao) {
        super(id);
        this.descricao = descricao;
    }

    public Pergunta(int id) {
        super(id);
    }

    public Pergunta(String descricao) {
        this.descricao = descricao;
    }

//    @Coluna(nome = "topicos")
//    private List<Topico> topicos;
}
