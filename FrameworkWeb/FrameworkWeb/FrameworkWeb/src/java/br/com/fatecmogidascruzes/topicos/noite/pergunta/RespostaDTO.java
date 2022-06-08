package br.com.fatecmogidascruzes.topicos.noite.pergunta;

public class RespostaDTO {
    
    private String tituloPergunta;
    private String descricaoPergunta;
    private int outroDado1;
    private double outroDado2;

    public RespostaDTO(String tituloPergunta, String descricaoPergunta, int outroDado1, double outroDado2) {
        this.tituloPergunta = tituloPergunta;
        this.descricaoPergunta = descricaoPergunta;
        this.outroDado1 = outroDado1;
        this.outroDado2 = outroDado2;
    }

    public String getTituloPergunta() {
        return tituloPergunta;
    }

    public void setTituloPergunta(String tituloPergunta) {
        this.tituloPergunta = tituloPergunta;
    }

    public String getDescricaoPergunta() {
        return descricaoPergunta;
    }

    public void setDescricaoPergunta(String descricaoPergunta) {
        this.descricaoPergunta = descricaoPergunta;
    }

    public int getOutroDado1() {
        return outroDado1;
    }

    public void setOutroDado1(int outroDado1) {
        this.outroDado1 = outroDado1;
    }

    public double getOutroDado2() {
        return outroDado2;
    }

    public void setOutroDado2(double outroDado2) {
        this.outroDado2 = outroDado2;
    }
    
    
    
}
