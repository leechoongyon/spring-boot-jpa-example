package com.example.jpa.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class NoGenerativeStrategy {
    @Id
    private Long id;

    @Column
    private String name;

    @Builder
    public NoGenerativeStrategy(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
