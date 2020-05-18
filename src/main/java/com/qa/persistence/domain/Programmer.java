package com.qa.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Objects;

@Entity
public class Programmer {
    @Id
    @GeneratedValue
    private long id; // sets an id, to be generated, long type.

    private String firstName;
    private String lastName;
    private String language;
    private String teamRoll;

    // constructor, filled
    public Programmer(String firstName, String lastName, String language, String teamRoll){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.language = language;
        this.teamRoll = teamRoll;
    }
    //blank Constructor (essential)
    public Programmer() {

    }
    //get/set/equals methods below

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTeamRoll() {
        return teamRoll;
    }

    public void setTeamRoll(String teamRoll) {
        this.teamRoll = teamRoll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programmer that = (Programmer) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(language, that.language) &&
                Objects.equals(teamRoll, that.teamRoll);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, language, teamRoll);
    }
}
