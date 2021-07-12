package com.example.jpa.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String teamName;

//    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<TeamMember> list = new ArrayList<>();

    @Builder
    public Team(Long id, String teamName, List<TeamMember> list) {
        this.id = id;
        this.teamName = teamName;
        this.list = list;
    }
}
