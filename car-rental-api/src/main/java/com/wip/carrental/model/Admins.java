package com.wip.carrental.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;


@Entity
@Table(name = "Admins")
public class Admins {
    @Id
    private int aEmpId;

    @Column
    private String aAddress;
    @Column
    @NonNull
    private String aName;
    @Column(unique = true)
    @NonNull
    private String aEmailId;
    @Column
    @NonNull
    private String aPassword;
    @Column(nullable = false, updatable = false)
   

    public int getaEmpId() {
        return aEmpId;
    }

    public void setaEmpId(int aEmpId) {
        this.aEmpId = aEmpId;
    }
    
    
    public String getaAddress() {
        return aAddress;
    }

    public void setaAddress(String aAddress) {
        this.aAddress = aAddress;
    }
    
    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getdEmailId() {
        return aEmailId;
    }

    public void setdEmailId(String aEmailId) {
        this.aEmailId = aEmailId;
    }

    public String getdPassword() {
        return aPassword;
    }

    public void setdPassword(String aPassword) {
        this.aPassword = aPassword;
    }


}
