package com.qa.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.dto.ProgrammerDTO;
import com.qa.persistence.domain.Programmer;
import com.qa.service.ProgrammerService;

@RestController
@RequestMapping("/programmer")
public class ProgrammerController {

    private ProgrammerService service;

    @Autowired
    public ProgrammerController(ProgrammerService service){
        super();
        this.service = service;
    }
    //noow for the rest service calls an their tied functions
    //starting with Crud

    @PostMapping("/createProgrammer")
    public ResponseEntity<ProgrammerDTO> createProgrammer(@RequestBody Programmer programmer){
        return new ResponseEntity<ProgrammerDTO>(this.service.createProgrammer(programmer), HttpStatus.CREATED);
    }
    //takes an id, passes for deletion, returns cargo cult programming
    //cruD
    @DeleteMapping("/deleteProgrammer/{id}")
    public ResponseEntity<?>deleteProgrammer(@PathVariable Long id) {
        return this.service.deleteProgrammer(id) ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() :ResponseEntity.noContent().build();
    }

    //cRud
    @GetMapping("/get/(id)")
    public ResponseEntity<ProgrammerDTO> getProgrammer(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findProgrammerByID(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProgrammerDTO>> getAllProgrammers(){
        return ResponseEntity.ok(this.service.readProgrammers());
    }

    @PutMapping("/updateProgrammer")
    public ResponseEntity<ProgrammerDTO> updateProgrammer(@PathParam("id") Long id, @RequestBody Programmer programmer) {
        return new ResponseEntity<ProgrammerDTO>(this.service.updateProgrammer(programmer,id), HttpStatus.ACCEPTED);
    }

}
