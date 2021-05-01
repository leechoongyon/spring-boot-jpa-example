package com.example.jpa.domain;

import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SampleWithAutoIncrement implements Persistable<SampleWithAutoIncrement> {

    @Id @GeneratedValue
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sample;

    public void setId(Long id) {
        this.id = id;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    @Override
    public SampleWithAutoIncrement getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
