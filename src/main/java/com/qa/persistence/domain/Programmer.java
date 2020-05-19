package com.qa.persistence.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Programmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // sets an id, to be generated, long type.

    private String firstName;
    private String lastName;
    private String language;
    private String teamRole;

    // constructor, filled
    public Programmer(String firstName, String lastName, String language, String teamRole){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.language = language;
        this.teamRole = teamRole;
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

    public String getTeamRole() {
        return teamRole;
    }

    public void setTeamRoll(String teamRoll) {
        this.teamRole = teamRoll;
    }

    @Override
    public  boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj==null)
            return false;
        if (getClass() != obj.getClass())
            return  false;
        Programmer other = (Programmer) obj;
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            } else if (!firstName.equals(other.firstName))
                return false;
        }
            if(lastName == null){
                if(other.lastName != null){
                    return false;
                } else if(!lastName.equals(other.lastName))
                    return false;
            }
            if(language == null){
                if(other.language != null) {
                    return false;
                }else if(!language.equals(other.language)){
                    return false;
                }
             if(teamRole == null){
                 if(other.teamRole != null) {
                     return false;
                 }else if (!teamRole.equals(other.teamRole)){
                     return false;
                 }
             }

            }
            return true;
        }


    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, language, teamRole);
    }
}
