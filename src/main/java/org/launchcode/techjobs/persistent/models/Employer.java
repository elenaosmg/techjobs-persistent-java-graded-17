package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity //EO annotated it as entity, because the class will be mapped to the table
public class Employer extends AbstractEntity {


    @NotBlank(message = "Field can not be blank")
    @Size(min = 1, max =30, message = "Location must be between 1 nad 30 characters")
private String location; //EO dec lare a variable

// TODO add a private property jobs of type List<Job> and initialize it
//  to an empty ArrayList. This list will represent the list of all items
//  in a given job (+need to add getters and setters)

    @OneToMany
    @JoinColumn (name = "employer_id") //EO foreign key
private List<Job> jobs = new ArrayList<>();


    //EO add constructors to create objects (empty for Hibernate)
    public Employer () {
    }

    public Employer (String location) {
        this.location = location;
    }


    public void addJob(Job job) {
        jobs.add(job);
    }


//EO public accessors are getters and setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }



}
