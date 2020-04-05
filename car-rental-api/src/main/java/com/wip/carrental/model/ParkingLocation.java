package com.wip.carrental.model;


import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "parking_locations")
public class ParkingLocation {


    @Id
    @GeneratedValue
    private Long locationId;

    @Column
    @NonNull
    private Integer capacity;

    @Column
    @NonNull
    private String city;

    @Column
    @NonNull
    private String address;

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @NonNull
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(@NonNull Integer capacity) {
        this.capacity = capacity;
    }

    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

}
