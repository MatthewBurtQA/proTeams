package com.qa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.dto.ProgrammerDTO;
import com.qa.exceptions.ProgrammerNotFoundException;
import com.qa.persistence.domain.Programmer;
import com.qa.persistence.repo.ProgrammerRepo;
import com.qa.utils.MyBeanUtils;

@Service
public class ProgrammerService {

    private ProgrammerRepo repo;
    private ModelMapper mapper;

    //constructor that is autowired
    @Autowired
    public ProgrammerService(ProgrammerRepo repo, ModelMapper mapper){
        this.repo = repo;
        this.mapper = mapper;
    }
    // map a programmer to a Data Transfer Object.

    private ProgrammerDTO mapToDTO(Programmer programmer){
        return this.mapper.map(programmer, ProgrammerDTO.class);

    }
    //create new programmer Data Transfer Object.

    public ProgrammerDTO createProgrammer(Programmer programmer){
        return mapToDTO(this.repo.save(programmer));
    }
    //What I wouldn't give to be deleted IRL. Imagine the complete ceaseation of existence and a release.

    public boolean deleteProgrammer(Long id) {
        if (!this.repo.existsById(id)) {
            throw new ProgrammerNotFoundException();
        } else {
            this.repo.deleteById(id);
            return this.repo.existsById(id);
        }
    }
        //method for finding a programmer, takes an input of the id, returns
        //programmer OR error(If not found).

    public ProgrammerDTO findProgrammerByID(Long id){
        return mapToDTO(this.repo.findById(id).orElseThrow(ProgrammerNotFoundException::new));
    }
    //Next up, the read functionality for reading the entire list. Uses stream. Don't cross them

    public List<ProgrammerDTO> readProgrammers() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    //finally, the update method, which will probably not work, because that's how life goes.
    public ProgrammerDTO updateProgrammer(Programmer programmer, Long id){
        Programmer toUpdate = this.repo.findById(id).orElseThrow(ProgrammerNotFoundException::new);
        MyBeanUtils.mergeNotNull(programmer, toUpdate);
        return this.mapToDTO(this.repo.save(toUpdate));
    }
}
