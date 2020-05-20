package com.qa.rest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.dto.TeamDTO;
import com.qa.persistence.domain.Team;
import com.qa.rest.TeamController;
import com.qa.service.TeamService;

@RunWith(MockitoJUnitRunner.class)
public class TeamControllerUnitTest {

    @InjectMocks
    private TeamController controller;

    @Mock
    private TeamService service;

    private List<Team> teamList;

    private Team testTeam;

    private Team testTeamWithID;

    private TeamDTO teamDTO;

    final long id = 1L;

    private ModelMapper mapper = new ModelMapper();


    private TeamDTO mapToDTO(Team team) {
        return this.mapper.map(team, TeamDTO.class);
    }


    @Before
    public void init() {
        this.teamList = new ArrayList<>();
        this.testTeam = new Team("dreamteam");
        this.teamList.add(testTeam);
        this.testTeamWithID = new Team(testTeam.getName());
        this.testTeamWithID.setId(id);
        this.teamDTO = this.mapToDTO(testTeamWithID);
    }

    @Test
    public void createTeamTest() {
        when(this.service.createTeam(testTeam)).thenReturn(this.teamDTO);

        assertEquals(new ResponseEntity<TeamDTO>(this.teamDTO, HttpStatus.CREATED), this.controller.createTeam(testTeam));

        verify(this.service, times(1)).createTeam(this.testTeam);
    }

    @Test
    public void deleteTeamTest() {
        this.controller.deleteTeam(id);

        verify(this.service, times(1)).deleteTeam(id);
    }

    @Test
    public void findTeamByIDTest() {
        when(this.service.findTeamByID(this.id)).thenReturn(this.teamDTO);

        assertEquals(new ResponseEntity<TeamDTO>(this.teamDTO, HttpStatus.OK), this.controller.getTeam(this.id));

        verify(this.service, times(1)).findTeamByID(this.id);
    }

    @Test
    public void getAllTeamsTest() {

        when(service.readTeams()).thenReturn(this.teamList.stream().map(this::mapToDTO).collect(Collectors.toList()));

        assertFalse("Controller has found NO TEAMS >:(", this.controller.getAllTeams().getBody().isEmpty());

        verify(service, times(1)).readTeams();
    }
}