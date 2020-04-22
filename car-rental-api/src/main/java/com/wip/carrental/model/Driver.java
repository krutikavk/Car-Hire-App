package com.wip.carrental.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;


import java.util.*;

@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @NonNull
    private String driverEmailId;

    @Column
    @NonNull
    private String driverLicense;

    @Column
    private String driverAddress;
    @Column
    @NonNull
    private String driverName;

    @Column
    @NonNull
    private String driverPassword;
    @Column(nullable = false, updatable = false)
    private Date driverMembershipStart;
    @Column(nullable = false)
    private Date driverMembershipEnd;
    
    @OneToMany(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "driver")
    private List<Reservation> reservation;


    @NonNull
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(@NonNull String driverName) {
        this.driverName = driverName;
    }

    @NonNull
    public String getDriverPassword() {
        return driverPassword;
    }

    public void setDriverPassword(@NonNull String driverPassword) {
        this.driverPassword = driverPassword;
    }

    public Date getDriverMembershipStart() {
        return driverMembershipStart;
    }

    public void setDriverMembershipStart(Date driverMembershipStart) {
        this.driverMembershipStart = driverMembershipStart;
    }

    public Date getDriverMembershipEnd() {
        return driverMembershipEnd;
    }

    public void setDriverMembershipEnd(Date driverMembershipEnd) {
        this.driverMembershipEnd = driverMembershipEnd;
    }

    public List<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }

    @NonNull
    public String getDriverEmailId() {
        return driverEmailId;
    }

    public void setDriverEmailId(@NonNull String driverEmailId) {
        this.driverEmailId = driverEmailId;
    }

    @NonNull
    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(@NonNull String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    //This will set start and end of the membership
    public void setdMembership() {
        Calendar cal = Calendar.getInstance();
        Date result = cal.getTime();
        this.driverMembershipStart = result;
        cal.add(Calendar.MONTH, 6);
        result = cal.getTime();
        this.driverMembershipEnd = result;
    }


}
