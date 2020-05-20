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

    }

}
