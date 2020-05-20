package com.qa.rest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.dto.ProgrammerDTO;
import com.qa.persistence.domain.Programmer;
import com.qa.persistence.repo.ProgrammerRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProgrammerControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private ProgrammerRepo repo;

    @Autowired
    private ModelMapper modelMapper;

    private ObjectMapper mapper = new ObjectMapper();

    private long id;

    private Programmer testProgrammer;

    private Programmer testProgrammerWithID;

    private ProgrammerDTO programmerDTO;

    private ProgrammerDTO mapToDTO(Programmer programmer) {
        return this.modelMapper.map(programmer, ProgrammerDTO.class);
    }

    @Before
    public void init() {
        this.repo.deleteAll();
        this.testProgrammer = new Programmer("Gerry", "Seinfeld", "Bahston","allegedComedian" );
        this.testProgrammerWithID = this.repo.save(this.testProgrammer);
        this.id = this.testProgrammerWithID.getId();
        this.programmerDTO = this.mapToDTO(testProgrammerWithID);
    }

    @Test
    public void testCreateProgrammer() throws Exception {
        String result = this.mock
                .perform(request(HttpMethod.POST, "/programmer/createProgrammer").contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(testProgrammer)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        assertEquals(this.mapper.writeValueAsString(programmerDTO), result);
    }

    @Test
    public void testDeleteProgrammer() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/programmer/deleteProgrammer/" + this.id)).andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllDucks() throws Exception {
        List<ProgrammerDTO> programmerList = new ArrayList<>();
        programmerList.add(this.programmerDTO);

        String content = this.mock.perform(request(HttpMethod.GET, "/programmer/getAll").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(this.mapper.writeValueAsString(programmerList), content);
    }

}
