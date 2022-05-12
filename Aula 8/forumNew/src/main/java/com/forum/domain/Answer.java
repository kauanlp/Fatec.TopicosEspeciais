package com.forum.domain;

import com.forum.persistence.annotations.Coluna;
import lombok.Data;

@Data
public class Answer extends DomainEntity{
    @Coluna(nome = "description")
    private String description;

    @Coluna(nome = "question")
    private Question question;


    public Answer(Integer id, String description, Question question) {
        super(id);
        this.description = description;
        this.question = question;
    }

    public Answer(String description) {
        this.description = description;
    }

    public Answer(String description, Question question) {
        this.description = description;
        this.question = question;
    }
}
