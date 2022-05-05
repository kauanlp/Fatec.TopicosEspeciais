package com.topicos.forum.dominio;

import com.topicos.forum.dominio.DomainEntity;
import com.topicos.forum.persistencia.anotacoes.Coluna;
import com.topicos.forum.persistencia.anotacoes.Tabela;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Tabela(nome = "respostas")
public class Resposta extends DomainEntity {
    @Coluna(nome = "descricao")
    private String descricao;
}
