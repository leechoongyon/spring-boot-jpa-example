package com.example.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IsNewSampleWithPrimitiveId {

    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }
}
