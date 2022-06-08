package br.com.fatecmogidascruzes.topicos.noite.pergunta;

import br.com.fatecmogidascruzes.topicos.noite.ModeloVisao;
import br.com.fatecmogidascruzes.topicos.noite.anotacao.Controladora;
import br.com.fatecmogidascruzes.topicos.noite.anotacao.MapeamentoRequisicao;
import br.com.fatecmogidascruzes.topicos.noite.anotacao.ParametroRequisicao;

@Controladora("/do/pergunta")
public class ControladoraPergunta {

    @MapeamentoRequisicao("/nova")
    public ModeloVisao UriToPerguntas(@ParametroRequisicao("outroDado1") int outroDado1,
            @ParametroRequisicao("outroDado2") double outroDado2,
            @ParametroRequisicao("pergunta") Pergunta pergunta) {

        System.out.println("Recebi: " + outroDado1);
        System.out.println("Recebi: " + outroDado2);
        System.out.println("Recebi: " + pergunta);
        
        return new ModeloVisao("resultado.jsp", "resposta", 
                new RespostaDTO(null == pergunta ? null : pergunta.getTitulo(), 
                        null == pergunta ? null : pergunta.getDescricao(), 
                        outroDado1, outroDado2));
    }

}
