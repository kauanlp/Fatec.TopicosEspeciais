package com.topicos.forum;

import com.topicos.forum.persistencia.anotacoes.Coluna;
import com.topicos.forum.persistencia.anotacoes.Id;

public class DomainEntity {
    @Id
    @Coluna(nome = "id")
    private int id;
}
