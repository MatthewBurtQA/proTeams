package com.qa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.dto.TeamDTO;
import com.qa.persistence.domain.Team;
import com.qa.persistence.repo.TeamRepo;
import com.qa.service.TeamService;
import org.springframework.ui.Model;


@RunWith(SpringRunner.class)
public class TeamServiceUnitTest {

    @InjectMocks
    private TeamService service;

    @Mock
    private TeamRepo repo;

    @Mock
    private ModelMapper mapper;

    private List<Team> teamList;
    private Team testTeam;
    private Team testTeamWithID;
    private TeamDTO teamDTO;
    final long id = 1l;

    @Before
    public void init(){
        this.teamList = new ArrayList<>();
        this.teamList.add(testTeam);
        this.testTeam = new Team("Test team!");
        this.testTeamWithID = new Team(testTeam.getName());
        this.testTeamWithID.setId(id);
        this.teamDTO = new ModelMapper().map(testTeamWithID, TeamDTO.class);

    }

    @Test
    public void createTeamTest() {
        when(this.repo.save(testTeam)).thenReturn(testTeamWithID);
        when(this.mapper.map(testTeamWithID, TeamDTO.class)).thenReturn(teamDTO);
        assertEquals(this.teamDTO, this.service.createTeam(testTeam));
        verify(this.repo, times(1)).save(this.testTeam);

    }

    @Test
    public void deleteTeamTest(){
        when(this.repo.existsById(id)).thenReturn(true, false);
        this.service.deleteTeam(id);
        verify(this.repo, times(1)).deleteById(id);
        verify(this.repo, times(2)).existsById(id);

    }
    @Test
    public void findTeamByIDTest(){
        when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testTeamWithID));
        when(this.mapper.map(testTeamWithID, TeamDTO.class)).thenReturn(teamDTO);
        assertEquals(this.teamDTO, this.service.findTeamByID(this.id));
        verify(this.repo, times(1)).findById(this.id);
    }


    @Test
    public void readTeamsTest() {

        when(repo.findAll()).thenReturn(this.teamList);
        when(this.mapper.map(testTeamWithID, TeamDTO.class)).thenReturn(teamDTO);

        assertFalse("Controller has found no TEAMS", this.service.readTeams().isEmpty());
        verify(repo, times(1)).findAll();
    }




}
