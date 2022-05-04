package br.com.fatecmogidascruzes.topicosbackend1;

import br.com.fatecmogidascruzes.topicosbackend1.persistencia.anotacoes.Coluna;
import br.com.fatecmogidascruzes.topicosbackend1.persistencia.anotacoes.Id;
import br.com.fatecmogidascruzes.topicosbackend1.persistencia.anotacoes.Tabela;
import br.com.fatecmogidascruzes.topicosbackend1.persistencia.anotacoes.Transiente;

@Tabela(nome = "agenda_contato")
public class Contato {

    @Id
    @Coluna(nome = "cod")
    private int codigo;
    @Coluna(nome = "nom")
    private String nome;
    @Transiente
    private String telefone;
    private String email;

    public Contato(int codigo) {
        this.codigo = codigo;
    }

    public Contato(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Contato(int codigo, String nome, String telefone, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
