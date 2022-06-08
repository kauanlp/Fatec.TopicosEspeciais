package br.com.fatecmogidascruzes.topicos.noite;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class CarregadorClasses {

    // encontrarClassesNoPacote("br.com.fatecmogidascruzes");
    public List<Class> encontrarClassesNoPacote(String nomePacote) throws IOException, ClassNotFoundException {

        System.out.println("Procurando classes a partir do pacote " + nomePacote + "...");

        ClassLoader carregadorClassesJava = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> conteudoPasta = carregadorClassesJava.getResources(nomePacote.replace(".", "/"));

        List<Class> resultado = new ArrayList<>();
        while (conteudoPasta.hasMoreElements()) {
            URL conteudo = conteudoPasta.nextElement();

            System.out.println("Tratando item " + conteudo.getFile() + "...");

            resultado.addAll(encontrarClassesEm(new File(conteudo.getFile()), nomePacote));
        }
        System.out.println("Todos os itens foram tratados.");

        return resultado;

    }

    public List<Class> encontrarClassesEm(File pastaAtual, String pacoteAtual) throws ClassNotFoundException {
        List<Class> resultado = new ArrayList<>();

        System.out.println("Procurando classes e subpacotes na pasta " + pastaAtual + " (" + pacoteAtual + ")...");

        File[] filhos = pastaAtual.listFiles();
        if (null == filhos || 0 == filhos.length) {
            System.out.println("A pasta atual não tem filhos");
            return resultado;
        }

        System.out.println("Encontrei " + filhos.length + " filhos");
        for (File filho : filhos) {
            System.out.println("Verificando o tipo do filho " + filho.getName());
            if (filho.isFile() && filho.getName().endsWith(".class")) {
                System.out.println("É classe: " + filho.getName());
                try {
                    resultado.add(carregarClasse(pacoteAtual, filho));
                } catch (Exception e) {
                    System.out.println("Não foi possível carregar " + filho.getName());
                }
            } else if (filho.isDirectory()) {
                System.out.println("É pasta: " + filho.getName());
                resultado.addAll(encontrarClassesEm(new File(pastaAtual, filho.getName()), getPacoteEClasse(pacoteAtual, filho.getName())));
            }
        }

        return resultado;
    }

    private Class carregarClasse(String pacote, File classe) throws ClassNotFoundException {
        return Class.forName(getPacoteEClasse(pacote, getNomeClasse(classe.getName())));
    }

    private String getPacoteEClasse(String pacote, String classe) {
        return pacote + "." + classe;
    }

    private String getNomeClasse(String nomeClasseComExtensao) {
        //return nomeClasseComExtensao;
        return nomeClasseComExtensao.split("\\.")[0];
    }

}
