package com.qa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.dto.TeamDTO;
import com.qa.persistence.domain.Team;
import com.qa.persistence.repo.TeamRepo;
import com.qa.service.TeamService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamServiceIntegrationTest {

    @Autowired
    private TeamService service;

    @Autowired
    private TeamRepo repo;

    private Team testTeam;

    private Team testTeamWithID;

    @Autowired
    private ModelMapper mapper;

    private TeamDTO mapToDTO(Team team){
        return this.mapper.map(team, TeamDTO.class);

    }

    @Before
    public void init(){
        this.testTeam = new Team("Doomed dudes");

        this.repo.deleteAll();
        //getting around auto-generated id's
        this.testTeamWithID = this.repo.save(this.testTeam);

    }



    @Test
    public void testDeleteTeam() {
        assertThat(this.service.deleteTeam(this.testTeamWithID.getId())).isFalse();
    }

    @Test
    public void testFindTeamByID() {
        assertThat(this.service.findTeamByID(this.testTeamWithID.getId())).isEqualTo(this.mapToDTO(this.testTeamWithID));
    }

    @Test
    public void testReadTeams() {
        assertThat(this.service.readTeams()).isEqualTo(Stream.of(this.mapToDTO(testTeamWithID)).collect(Collectors.toList()));
    }

}
