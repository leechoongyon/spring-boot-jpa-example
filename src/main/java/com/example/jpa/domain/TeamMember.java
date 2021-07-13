package com.example.jpa.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class TeamMember {
    @Id @Column(name = "team_member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public TeamMember(Long id, String name, Team team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }
}
