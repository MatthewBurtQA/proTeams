package com.qa.dto;

import java.util.Objects; // if errors, remove, and remove equals/tohash code. strange.

public class ProgrammerDTO {

    //programmer variables

    private long id;

    private String firstName;
    private String lastName;
    private String language;
    private String teamRole;

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

    public void setTeamRole(String teamRole) {
        this.teamRole = teamRole;
    }

    @Override
    public String toString() {
        return "ProgrammerDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", language='" + language + '\'' +
                ", teamRole='" + teamRole + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammerDTO that = (ProgrammerDTO) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(language, that.language) &&
                Objects.equals(teamRole, that.teamRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, language, teamRole);
    }
}
