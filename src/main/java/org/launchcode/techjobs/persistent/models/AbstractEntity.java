package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;


@MappedSuperclass // EO added to make it a superclass for those inherriting from it
public abstract class AbstractEntity {

    @Id //EO make "id" primary field
    @GeneratedValue(strategy = GenerationType.IDENTITY) //EO explains how id will be generated (automatically assigned in this case)

    private int id;

//EO added annotations to make name field in Job, Employer, SKill not blank and 1 char and more)
    @NotBlank(message = "Field can not be blank")
    @Size (min = 1, message = "Name must be at least 1 character")

    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
