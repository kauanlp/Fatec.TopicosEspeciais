package com.topicos.forum;

import com.topicos.forum.persistencia.anotacoes.Coluna;
import com.topicos.forum.persistencia.anotacoes.Id;
import com.topicos.forum.persistencia.anotacoes.Tabela;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Tabela(nome = "perguntas")
public class Pergunta extends DomainEntity{

    @Coluna(nome = "descricao")
    private String descricao;

    @Coluna(nome = "topicos")
    private List<Topico> topicos;
}
