package com.example.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class SampleWithVersion {

    @Id
    private Long id;

    @Version
    private Long version;

    public void setId(Long id) {
        this.id = id;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
