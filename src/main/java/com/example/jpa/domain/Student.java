package com.example.jpa.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    @Setter
    private Long id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String address;

    @Column
    private String etc;

    @Builder
    public Student(Long id, String name, int age, String address, String etc) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.etc = etc;
    }
}
