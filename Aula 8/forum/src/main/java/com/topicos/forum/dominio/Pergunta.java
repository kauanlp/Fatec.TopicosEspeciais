package com.topicos.forum.dominio;

import com.topicos.forum.persistencia.anotacoes.Coluna;
import com.topicos.forum.persistencia.anotacoes.Tabela;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

   // private List<Resposta> respostas;

    public Pergunta(int id, String titulo, String descricao, Date dtCadastro) {
        super(id, dtCadastro);
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Pergunta(int id) {
        super(id);
    }

    public Pergunta(String titulo, String descricao, Date dtCadastro) {
        super(dtCadastro);
        this.titulo = titulo;
        this.descricao = descricao;
    }

<<<<<<< Updated upstream

=======
    public Pergunta(int id, String descricao) {
        super(id);
        this.descricao = descricao;
    }
>>>>>>> Stashed changes

//    @Coluna(nome = "topicos")
//    private List<Topico> topicos;
}
