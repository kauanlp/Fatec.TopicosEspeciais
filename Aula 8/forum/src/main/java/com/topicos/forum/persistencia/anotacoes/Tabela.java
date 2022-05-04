package com.topicos.forum.persistencia.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Tabela {

    public static final String TABELA_SEM_NOME = "!__SEM_NOME__!";

    public String nome() default TABELA_SEM_NOME;

}
