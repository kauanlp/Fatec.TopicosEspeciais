package com.forum.domain;

import com.forum.persistence.annotations.Coluna;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question extends DomainEntity{
    @Coluna(nome = "title")
    private String title;

    @Coluna(nome = "description")
    private String description;

    @Coluna(nome = "tag")
    private String tag;

    public Question(int id, String title, String description, String tag, Date registerDate) {
        super(id, registerDate);
        this.title = title;
        this.description = description;
        this.tag = tag;
    }

    public Question(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
