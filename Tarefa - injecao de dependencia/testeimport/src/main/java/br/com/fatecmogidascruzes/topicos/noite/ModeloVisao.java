package br.com.fatecmogidascruzes.topicos.noite;

public class ModeloVisao {

    private String visao;
    private String nomeModelo;
    private Object modelo;

    public ModeloVisao(String visao, String nomeModelo, Object modelo) {
        this.visao = visao;
        this.nomeModelo = nomeModelo;
        this.modelo = modelo;
    }

    public String getVisao() {
        return visao;
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public Object getModelo() {
        return modelo;
    }

}
