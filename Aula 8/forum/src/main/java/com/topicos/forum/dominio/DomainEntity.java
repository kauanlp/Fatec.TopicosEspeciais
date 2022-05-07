package com.topicos.forum.dominio;

import com.topicos.forum.persistencia.anotacoes.Coluna;
import com.topicos.forum.persistencia.anotacoes.Id;
import lombok.Data;

import java.util.Date;

@Data
public class DomainEntity {
    @Id
    @Coluna(nome = "id")
    private int id;
    private Date dtCadastro;

    public DomainEntity() {
        this.dtCadastro = new Date();
    }
    public DomainEntity(Integer id) {
        this.id = id;
    }
    public DomainEntity(Integer id, Date dtCadastro) {
        this.id = id;
        this.dtCadastro = dtCadastro;
    }
    public DomainEntity(Date dtCadastro){
        this.dtCadastro = dtCadastro;
    }
}
