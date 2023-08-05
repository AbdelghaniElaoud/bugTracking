package com.example.bugreporting.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    // Constructors, getters, setters, and other methods (if needed)

    // Constructors
    public Project() {
    }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    // (Omitted for brevity, you can use your IDE to generate them)


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
