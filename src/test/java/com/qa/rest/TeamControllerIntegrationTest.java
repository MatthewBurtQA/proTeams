package com.qa.rest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.persistence.domain.Team;
import com.qa.persistence.repo.TeamRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeamControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private TeamRepo repo;

    private ObjectMapper mapper = new ObjectMapper();

    private long id;

    private Team testTeam;

    private Team testTeamWithID;

    @Before
    public void init() {
        this.repo.deleteAll();
        this.testTeam = new Team("Cumanias Last Riders");
        this.testTeamWithID = this.repo.save(this.testTeam);
        this.id = this.testTeamWithID.getId();
    }

    @Test
    public void testCreatePond() throws Exception {
        String result = this.mock
                .perform(request(HttpMethod.POST, "/team/createTeam").contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(testTeam)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        assertEquals(this.mapper.writeValueAsString(testTeamWithID), result);
    }

    @Test
    public void testDeletePond() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/team/deleteTeam/" + this.id)).andExpect(status().isNoContent());
    }

    @Test
    public void testGetTeam() throws Exception {
        String content = this.mock
                .perform(request(HttpMethod.GET, "/team/get/" + this.id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(this.mapper.writeValueAsString(this.testTeam), content);
    }

    @Test
    public void testGetAllTeamss() throws Exception {
        List<Team> teamList = new ArrayList<>();
        teamList.add(this.testTeamWithID);

        String content = this.mock.perform(request(HttpMethod.GET, "/team/getAll").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(this.mapper.writeValueAsString(teamList), content);
    }

    @Test
    public void testUpdatePond() throws Exception {
        Team newTeam = new Team("RandalRandom");
        Team updatedTeam = new Team(newTeam.getName());
        updatedTeam.setId(this.id);

        String result = this.mock
                .perform(request(HttpMethod.PUT, "/team/updatePond/?id=" + this.id).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newTeam)))
                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();

        assertEquals(this.mapper.writeValueAsString(updatedTeam), result);
    }

}
