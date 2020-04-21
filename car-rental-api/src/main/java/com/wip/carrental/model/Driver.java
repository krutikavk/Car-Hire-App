package com.wip.carrental.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;


import java.util.*;

@Entity
@Table(name = "driver")
public class Driver {

    @Id
    private String dLicense;

    @Column
    private String dAddress;
    @Column
    @NonNull
    private String dName;
    @Column(unique = true)
    @NonNull
    private String driverEmailId;
    @Column
    @NonNull
    private String dPassword;
    @Column(nullable = false, updatable = false)
    private Date dMembershipStart;
    @Column(nullable = false)
    private Date dMembershipEnd;


    public String getdLicense() {
        return dLicense;
    }

    public void setdLicense(String dLicense) {
        this.dLicense = dLicense;
    }

    public String getdAddress() {
        return dAddress;
    }

    public void setdAddress(String dAddress) {
        this.dAddress = dAddress;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdDriverEmailId() {
        return driverEmailId;
    }

    public void setdDriverEmailId(String dEmailId) {
        this.driverEmailId = dEmailId;
    }

    public String getdPassword() {
        return dPassword;
    }

    public void setdPassword(String dPassword) {
        this.dPassword = dPassword;
    }

    public Date getdMembershipStart() {
        return dMembershipStart;
    }

    //This will set start and end of the membership
    public void setdMembership() {
        Calendar cal = Calendar.getInstance();
        Date result = cal.getTime();
        this.dMembershipStart = result;
        cal.add(Calendar.MONTH, 6);
        result = cal.getTime();
        this.dMembershipEnd = result;
    }

    public Date getdMembershipEnd() {
        return dMembershipEnd;
    }

    public void setdMembershipStart(Date dMembershipStart) {
        this.dMembershipStart = dMembershipStart;
    }

    public void setdMembershipEnd(Date dMembershipEnd) {
        this.dMembershipEnd = dMembershipEnd;
    }

}
