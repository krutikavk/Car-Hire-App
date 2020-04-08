package com.wip.carrental.model;


import org.springframework.lang.NonNull;

import javax.persistence.*;


@Entity
@Table(name = "admins")
public class Admins {
    
    @Column
    @NonNull
    private int aEmpId;

    @Column
    private String aAddress;
    @Column
    @NonNull
    private String aName;
   
    @Id
    @NonNull
    private String aEmailId;
    
    @Column
    @NonNull
    private String aPassword;
   

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

    public String getaEmailId() {
        return aEmailId;
    }

    public void setaEmailId(String aEmailId) {
        this.aEmailId = aEmailId;
    }

    public String getaPassword() {
        return aPassword;
    }

    public void setaPassword(String aPassword) {
        this.aPassword = aPassword;
    }


}
