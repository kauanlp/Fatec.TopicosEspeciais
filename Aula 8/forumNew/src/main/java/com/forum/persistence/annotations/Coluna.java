package com.forum.persistence.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Coluna {

    public static final String COLUNA_SEM_NOME = "!__SEM_NOME__!";

    public String nome() default COLUNA_SEM_NOME;

}
