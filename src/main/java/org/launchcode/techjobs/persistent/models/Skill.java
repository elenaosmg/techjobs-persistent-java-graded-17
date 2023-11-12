package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {


    @NotBlank(message = "Field can not be blank")
    @Size(max =500, message = "Description must be no more then 500 characters")

    public String description; // EO added field

    @ManyToMany (mappedBy = "skills")
    private final List<Job> job = new ArrayList<>();
//    public List<Job> job;


//EO add constructors:

    public Skill (){
            }

    public Skill(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJob() {
        return job;
    }

//    public void setJob(List<Job> job) {
//        this.jobs = job;
//    }
}



