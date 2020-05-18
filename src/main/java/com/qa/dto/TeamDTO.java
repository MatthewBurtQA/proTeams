package com.qa.dto;

import java.util.ArrayList;
import java.util.List;


public class TeamDTO {

    //the two teamvars, next up, arraylist then constructor iirc
    private Long id;
    private String name;

    private List<ProgrammerDTO> programmers = new ArrayList<>();

    //constructor
    public TeamDTO(String name) {
        this.name = name; // the one and only
    }
    //empty constructor next
    public TeamDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProgrammerDTO> getProgrammers() {
        return programmers;
    }

    public void setProgrammers(List<ProgrammerDTO> programmers) {
        this.programmers = programmers;
    }
}
