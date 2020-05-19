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

import com.qa.dto.ProgrammerDTO;
import com.qa.persistence.domain.Programmer;
import com.qa.persistence.repo.ProgrammerRepo;
import com.qa.service.ProgrammerService;

// RunWith essentially boots the springrunner class for testing purposes, and
//mocks are used to create simulated instances on local for testing purposes,
//which go to the Helheim  during garbage collection

@RunWith(SpringRunner.class)
public class ProgrammerServiceUnitTest {

    @InjectMocks
    private ProgrammerService service;

    @Mock
    private ProgrammerRepo repo;

    @Mock
    private ModelMapper mapper;

    //programmer mock details instantiation
    private List<Programmer> programmerList;
    private Programmer testProgrammer;
    private Programmer testProgrammerWithID;
    private ProgrammerDTO programmerDTO;

    final long id = 1L; // just a default long of 1, which is the first ID auto genned by application, so use 1

    //SET UP FOR TESTING
    // CREATE A TEST CASE THEN TEST FUNCTIONS ON IT.
    @Before
    public void init() {
        this.programmerList = new ArrayList<>();
        this.programmerList.add(testProgrammer);
        this.testProgrammer = new Programmer("Mattias", "Bartolomeo", "LatinJava", "Pontifex");
        this.testProgrammerWithID = new Programmer(testProgrammer.getFirstName(), testProgrammer.getLastName(), testProgrammer.getLanguage(), testProgrammer.getTeamRole());
        this.testProgrammerWithID.setId(id);// auto gen didnt work so manual it is. . .
        this.programmerDTO = new ModelMapper().map(testProgrammerWithID, ProgrammerDTO.class);
    }

    @Test
    public void createProgrammerTest() {
        when(this.repo.save(testProgrammer)).thenReturn(testProgrammerWithID);
        when(this.mapper.map(testProgrammerWithID, ProgrammerDTO.class)).thenReturn(programmerDTO);

        //now i need to validate that it worked
        assertEquals(this.programmerDTO, this.service.createProgrammer(testProgrammer));
        verify(this.repo, times(1)).save(this.testProgrammer);
    }


}