package com.qa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.dto.TeamDTO;
import com.qa.exceptions.TeamNotFoundException;
import com.qa.persistence.domain.Programmer;
import com.qa.persistence.domain.Team;
import com.qa.persistence.repo.ProgrammerRepo;
import com.qa.persistence.repo.TeamRepo;

@Service
public class TeamService {

    private TeamRepo repo;
    private ProgrammerRepo programmerRepo;
    private ModelMapper mapper;

    @Autowired
    public TeamService(TeamRepo repo, ProgrammerRepo programmerRepo, ModelMapper mapper){
        this.repo = repo;
        this.programmerRepo = programmerRepo;
        this.mapper = mapper;
    }

    // map the team to a Data Transfer Object
    public TeamDTO mapToDTO(Team team){
        return this.mapper.map(team, TeamDTO.class);
    }

    //saves input arg to DTO using the mapper
    public TeamDTO createTeam(Team team){
        return this.mapToDTO(this.repo.save(team));
    }

    public boolean deleteTeam(Long id){
        if (!this.repo.existsById(id)){
            throw new TeamNotFoundException(); // if this does not equals a repo that exists by this id then delete
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }
    //takes ID, finds team with assosciated ID
    public TeamDTO findTeamByID(Long id) {
        return this.mapToDTO(this.repo.findById(id).orElseThrow(() -> new TeamNotFoundException()));
    }

    public List<TeamDTO> readTeams() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    //this is the method that'll prolly not work but eh oh
    public TeamDTO updateTeam(Team programmer, Long id) {
        Team toUpdate = this.repo.findById(id).orElseThrow(() -> new TeamNotFoundException());
        toUpdate.setName(programmer.getName());
        return this.mapToDTO(this.repo.save(toUpdate));
    }
    //finds repo by ID, or throw error, THEN
    //make new programmer with input, and then get the list of programmers and save it to the list.
    public TeamDTO addProgrammerToTeam(Long id, Programmer programmer) {
        Team toUpdate = this.repo.findById(id).orElseThrow(() -> new TeamNotFoundException());
        Programmer newProgrammer = this.programmerRepo.save(programmer);
        toUpdate.getProgrammers().add(programmer);
        return this.mapToDTO(this.repo.saveAndFlush(toUpdate));
    }

}
