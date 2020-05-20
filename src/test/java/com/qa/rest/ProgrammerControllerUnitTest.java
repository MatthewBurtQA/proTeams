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

import com.qa.dto.ProgrammerDTO;
import com.qa.persistence.domain.Programmer;
import com.qa.rest.ProgrammerController;
import com.qa.service.ProgrammerService;

@RunWith(MockitoJUnitRunner.class)
public class ProgrammerControllerUnitTest {

    @InjectMocks
    private ProgrammerController controller;

    @Mock
    private ProgrammerService service;

    private List<Programmer> programmerList;

    private Programmer testProgrammer;

    private Programmer testProgrammerWithID;

    private ProgrammerDTO programmerDTO;

    final long id = 1L;

    private ModelMapper mapper = new ModelMapper();


    private ProgrammerDTO mapToDTO(Programmer programmer) {
        return this.mapper.map(programmer, ProgrammerDTO.class);
    }


    @Before
    public void init() {
        this.programmerList = new ArrayList<>();
        this.testProgrammer = new Programmer("One", "two","three", "four");
        this.programmerList.add(testProgrammer);
        this.testProgrammerWithID = new Programmer(testProgrammer.getFirstName(), testProgrammer.getLastName(), testProgrammer.getLanguage(), testProgrammer.getTeamRole());
        this.testProgrammerWithID.setId(id);
        this.programmerDTO = this.mapToDTO(testProgrammerWithID);
    }

    @Test
    public void createProgrammerTest() {
        when(this.service.createProgrammer(testProgrammer)).thenReturn(this.programmerDTO);

        assertEquals(new ResponseEntity<ProgrammerDTO>(this.programmerDTO, HttpStatus.CREATED), this.controller.createProgrammer(testProgrammer));

        verify(this.service, times(1)).createProgrammer(this.testProgrammer);
    }

    @Test
    public void deleteProgrammerTest() {
        this.controller.deleteProgrammer(id);

        verify(this.service, times(1)).deleteProgrammer(id);
    }

    @Test
    public void findProgrammerByIDTest() {
        when(this.service.findProgrammerByID(this.id)).thenReturn(this.programmerDTO);

        assertEquals(new ResponseEntity<ProgrammerDTO>(this.programmerDTO, HttpStatus.OK), this.controller.getProgrammer(this.id));

        verify(this.service, times(1)).findProgrammerByID(this.id);
    }

    @Test
    public void getAllProgrammerTest() {

        when(service.readProgrammers()).thenReturn(this.programmerList.stream().map(this::mapToDTO).collect(Collectors.toList()));

        assertFalse("Controller has found NO PROGRAMMAZ >:(", this.controller.getAllProgrammers().getBody().isEmpty());

        verify(service, times(1)).readProgrammers();
    }
}