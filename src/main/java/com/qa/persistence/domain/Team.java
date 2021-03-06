package com.qa.persistence.domain;

import java.util.List;
import javax.persistence.*;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // sets an ID, to be generated auto, of type long

    private String name; // a teams name. all relevant team details are observed by the programmers within

    @OneToMany
    private List <Programmer> programmers;

    // empty constructor
    public Team(){

    }
    public Team(String name){
        super();
        this.name = name;

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

    public List<Programmer> getProgrammers() {
        return programmers;
    }

    public void setProgrammers(List<Programmer> programmers) {
        this.programmers = programmers;
    }
}
