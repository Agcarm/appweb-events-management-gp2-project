package com.gp2.appwebeventsmanagementgp2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "venue")
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

    public venue(Long id, String name, String country, String city, String address, Double latitude, Double longitude,
            byte[] image) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
    }

    public venue() {
        //TODO Auto-generated constructor stub
    }

    public venue(Object object, String string, Object object2, String string2, Object object3, Object object4) {
        //TODO Auto-generated constructor stub
    }

    @Column(name = "longitude")
    private Double longitude;
    
    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] image;

    public venue(Long id, String name, String country, String city, String address, Double latitude, Double longitude,
            byte[] image) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
    }
    
    
}
