package com.example.jpa.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AutoCreateTime extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String test;

    @Builder
    public AutoCreateTime(String test) {
        this.test = test;
    }
}
