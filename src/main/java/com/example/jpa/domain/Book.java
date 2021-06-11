package com.example.jpa.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Student student;

    @Builder
    public Book(Long id, String name, Long price, Student student) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.student = student;
    }
}
