package com.example.jpa.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@SequenceGenerator(
        name="TEST_SEQUENCE_GENERATOR", // 제너레이터명
        sequenceName="TEST_SEQUENCE", // 시퀀스명
        initialValue= 1, // 시작 값
        allocationSize= 1 // 할당할 범위 사이즈
)
public class SequenceStrategy {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TEST_SEQUENCE_GENERATOR")
    private Long id;

    @Column
    private String name;

    @Builder
    public SequenceStrategy(String name) {
        this.name = name;
    }
}
