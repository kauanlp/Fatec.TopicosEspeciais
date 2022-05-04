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

    @Coluna(nome = "descricao")
    private String descricao;

    public Pergunta(int id, String descricao, Date dtCadastro) {
        super(id, dtCadastro);
        this.descricao = descricao;
    }

//    @Coluna(nome = "topicos")
//    private List<Topico> topicos;
}
