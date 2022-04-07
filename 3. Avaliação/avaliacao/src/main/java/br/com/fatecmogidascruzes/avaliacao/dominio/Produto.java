package br.com.fatecmogidascruzes.avaliacao.dominio;

public class Produto {

    private int id;
    private String nome;
    private String descricao;
    private Double precoUnitario;
    private String linkFoto;

    public Produto(int id) {
        this.id = id;
    }

    public Produto(String nome, String descricao, Double precoUnitario, String linkFoto) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.linkFoto = linkFoto;
    }

    public Produto(int id, String nome, String descricao, Double precoUnitario, String linkFoto) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.linkFoto = linkFoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getLinkFoto() {
        return linkFoto;
    }

    public void setLinkFoto(String linkFoto) {
        this.linkFoto = linkFoto;
    }
}
