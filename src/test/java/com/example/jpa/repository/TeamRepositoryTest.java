package com.example.jpa.repository;


import com.example.jpa.domain.Team;
import com.example.jpa.domain.TeamMember;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("in-memory-server")
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Test
//    @Transactional
    @Rollback(false)
    public void 저장_테스트() throws Exception {

        // given
        List<TeamMember> teamMembers = new ArrayList<>();
        TeamMember teamMemer = TeamMember.builder()
                .name("testMember1")
//                .team(team)
                .build();

        teamMembers.add(teamMemer);


        TeamMember teamMemer2 = TeamMember.builder()
                .name("testMember2")
//                .team(team)
                .build();
        teamMembers.add(teamMemer2);

        Team team = Team.builder()
                .teamName("testName")
                .list(teamMembers)
                .build();

        teamRepository.save(team);

//        List<Team> list = teamRepository.findAll();
//        List<TeamMember> teamMemberList = list.get(0).getList();
//        Assert.assertThat(teamMemberList.size(), is(2));
    }
}
