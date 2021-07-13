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

    // 양방향 매핑
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    // 단방향 매핑. 조인 테이블로 동작. 맵핑 테이블 생성
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<TeamMember> teamMembers = new ArrayList<>();

    @Builder
    public Team(Long id, String teamName, List<TeamMember> teamMembers) {
        this.id = id;
        this.teamName = teamName;
        this.teamMembers = teamMembers;
    }

    public void addTeamMember(String name) {
        if (this.teamMembers == null) {
            this.teamMembers = new ArrayList<>();
        }
        this.teamMembers.add(
                TeamMember.builder()
                        .name(name)
                        .team(this)
                        .build()
        );
    }
}
