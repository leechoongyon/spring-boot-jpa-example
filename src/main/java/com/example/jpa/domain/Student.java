package com.example.jpa.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    @Builder
    public Student(Long id, String name, int age, String address, String etc, List<Book> books) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.etc = etc;
        this.books = books;
    }
}
