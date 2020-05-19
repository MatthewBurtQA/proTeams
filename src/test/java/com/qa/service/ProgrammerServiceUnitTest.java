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

    //first we start with creation, now that set up works.
    @Test
    public void createProgrammerTest() {
        when(this.repo.save(testProgrammer)).thenReturn(testProgrammerWithID);
        when(this.mapper.map(testProgrammerWithID, ProgrammerDTO.class)).thenReturn(programmerDTO);

        //now i need to validate that it worked
        assertEquals(this.programmerDTO, this.service.createProgrammer(testProgrammer));
        verify(this.repo, times(1)).save(this.testProgrammer);
    }

    //delete test case: Check item exists by ID, call delete method on that item, then verify
    //that it's been deleted

    @Test
    public void deleteProgrammerTest(){
        when(this.repo.existsById(id)).thenReturn(true, false);
        this.service.deleteProgrammer(id);
        verify(this.repo, times(1)).deleteById(id);
        verify(this.repo, times(2)).existsById(id);
    }

    //check that the mock instance with ID 1 can be found, assert that it has been found using find by id

    @Test
    public void findProgrammerByIDTest() {
        when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testProgrammerWithID));
        when(this.mapper.map(testProgrammerWithID, ProgrammerDTO.class)).thenReturn(programmerDTO);
        assertEquals(this.programmerDTO, this.service.findProgrammerByID(id));
        verify(this.repo, times(1)).findById(this.id);
    }

    @Test
    public void readProgrammerTest(){
        when(repo.findAll()).thenReturn(this.programmerList);
        when(this.mapper.map(testProgrammerWithID, ProgrammerDTO.class)).thenReturn(programmerDTO);
        assertFalse("Controller has found no programmers", this.service.readProgrammers().isEmpty());

        verify(repo, times(1)).findAll();
    }



}