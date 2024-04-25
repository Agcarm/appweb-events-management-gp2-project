package com.gp2.appwebeventsmanagementgp2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Setter
@Table(name = "venues")
public class venue {
    @Id
    @GeneratedValue
    @Column(name = "idvenues")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;
    
    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] image;
}
