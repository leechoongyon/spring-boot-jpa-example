package com.example.jpa.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "student_id")
    @Setter
    private Long id;

    @Column
    private String name;

    @Builder
    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
