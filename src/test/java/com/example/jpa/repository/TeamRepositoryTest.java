package com.example.jpa.repository;


import com.example.jpa.domain.Team;
import com.example.jpa.domain.TeamMember;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("mysql-server")
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

//    @Test
//    public void 단방향_OneToMany_테스트() throws Exception {
//        // given
//        List<TeamMember> teamMembers = new ArrayList<>();
//        TeamMember teamMemer = TeamMember.builder()
//                .name("testMember1")
//                .build();
//
//        teamMembers.add(teamMemer);
//
//
//        TeamMember teamMemer2 = TeamMember.builder()
//                .name("testMember2")
//                .build();
//        teamMembers.add(teamMemer2);
//
//        Team team = Team.builder()
//                .teamName("testName")
//                .teamMembers(teamMembers)
//                .build();
//
//        // when
//        Team result = teamRepository.save(team);
//
//        // then
//        Assert.assertThat(result.getTeamMembers().size(), is(2));
//    }

    @Test
    @Transactional
    public void 양방향_OneToMany_테스트() throws Exception {

        // given
        Team team = Team.builder()
                .teamName("testName")
                .build();
        team.addTeamMember("teamMember11");
        team.addTeamMember("teamMember22");

        // when
        Team result = teamRepository.save(team);

        // then
        Assert.assertThat(result.getTeamMembers().size(), is(2));
    }
}
