package com.forum.domain;

import com.forum.persistence.annotations.Coluna;
import com.forum.persistence.annotations.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainEntity {
    @Id
    @Coluna(nome = "id")
    private Integer id;

    @Coluna(nome = "registerDate")
    private Date registerDate = new Date();

    public DomainEntity(Integer id) {
        this.id = id;
    }
}
