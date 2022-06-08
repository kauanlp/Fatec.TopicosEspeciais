package br.com.fatecmogidascruzes.topicos.noite;

import java.lang.reflect.Method;

public class MapeamentoClasseMetodo {

    private Class classe;
    private Method metodo;

    public MapeamentoClasseMetodo(Class classe, Method metodo) {
        this.classe = classe;
        this.metodo = metodo;
    }
    
    public Class getClasse() {
        return classe;
    }

    public void setClasse(Class classe) {
        this.classe = classe;
    }

    public Method getMetodo() {
        return metodo;
    }

    public void setMetodo(Method metodo) {
        this.metodo = metodo;
    }
   
}
