package com.gp2.appwebeventsmanagementgp2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Setter
@Table(name = "type")
public class type {
    @Id
    @GeneratedValue
    @Column(name = "idtype")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "colour", unique = true)
    private String colour;

    public type(Long id, String name, String colour) {
        this.id = id;
        this.name = name;
        this.colour = colour;
        
    }

    public type() {
    }

    public type(Object object, String string, Object object2) {
    }
}


