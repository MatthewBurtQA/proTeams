package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.dto.ProgrammerDTO;
import com.qa.persistence.domain.Programmer;
import com.qa.persistence.repo.ProgrammerRepo;
import com.qa.service.ProgrammerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgrammerServiceIntegrationTest {

    @Autowired
    private ProgrammerService service;
    @Autowired
    private ProgrammerRepo repo;

    private Programmer testProgrammer;
    private Programmer testProgrammerWithID; // its stuff like this that lead to two constructors ¬_¬

    @Autowired
    private ModelMapper mapper;

    private ProgrammerDTO mapToDTO(Programmer programmer){
        return this.mapper.map(programmer, ProgrammerDTO.class);
    }
    // time for set up of the before it runs stuff
    @Before
    public void init(){
        this.testProgrammer = new Programmer("Matty", "Burt","C","Khan");
        this.repo.deleteAll(); // clean workspace
        this.testProgrammerWithID = this.repo.save(this.testProgrammer); // saves the test programmer as a programmer so it can now be played with
    // all done with the preamble
    }
    //testtime, yay. (-___-')

    @Test
    public void testCreateProgrammer(){
        assertEquals(this.mapToDTO(this.testProgrammerWithID), this.service.createProgrammer(testProgrammer));
    }
    //if only i was as lucky as the programmer in the below test
    @Test
    public void testDeleteProgrammer(){
        assertThat(this.service.deleteProgrammer(this.testProgrammerWithID.getId())).isFalse();

    }
    @Test
    public void testFindProgrammerByID(){
        assertThat(this.service.findProgrammerByID(this.testProgrammerWithID.getId())).isEqualTo(this.mapToDTO(this.testProgrammerWithID));

    }
    @Test
    public void testReadProgrammers(){

        assertThat(this.service.readProgrammers()).isEqualTo(Stream.of(this.mapToDTO(testProgrammerWithID)).collect(Collectors.toList()));
    }

//    @Test
//    public void testUpdateProgrammer() {
//        Programmer newProgrammer = new Programmer("Matty", "Burt","C","Khan");
//        Programmer updatedProgrammer = new Programmer(newProgrammer.getFirstName(), newProgrammer.getLastName(),newProgrammer.getLanguage(),newProgrammer.getTeamRole());
//        updatedProgrammer.setId(this.testProgrammerWithID.getId());
//
//        assertThat(this.service.updateProgrammer(newProgrammer, this.testProgrammerWithID.getId())).isEqualTo(this.mapToDTO(updatedProgrammer));    }
}
