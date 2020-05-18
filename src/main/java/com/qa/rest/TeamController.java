package com.qa.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.dto.TeamDTO;
import com.qa.persistence.domain.Programmer;
import com.qa.persistence.domain.Team;
import com.qa.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController {

    private TeamService service; // instantiate a service to run.

    //instantiate the constructor
    @Autowired
    public TeamController(TeamService service){
        super();
        this.service = service;
    }
    //Crud
    @PostMapping("/createTeam")
    public ResponseEntity<TeamDTO> createTeam(@RequestBody Team team) {
        return new ResponseEntity<TeamDTO>(this.service.createTeam(team), HttpStatus.CREATED);
    }
    //cruD

    @DeleteMapping("/deletePond/{id}")
    public ResponseEntity<TeamDTO> deleteTeam(@PathVariable Long id){
        return this.service.deleteTeam(id)  ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }
    //cRud

    @GetMapping("/get/{id}")
    public ResponseEntity<TeamDTO> getTeam(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findTeamByID(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        return ResponseEntity.ok(this.service.readTeams());
    }

    @PutMapping("/updateTeam")
    public ResponseEntity<TeamDTO> updateTeam(@PathParam("id") Long id, @RequestBody Team team){
        return new ResponseEntity<TeamDTO>(this.service.updateTeam(team, id), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<TeamDTO> addProgrammerToTeam(@PathVariable Long id, @RequestBody Programmer programmer) {
        return new ResponseEntity<TeamDTO>(this.service.addProgrammerToTeam(id, programmer), HttpStatus.ACCEPTED);
    }
}
