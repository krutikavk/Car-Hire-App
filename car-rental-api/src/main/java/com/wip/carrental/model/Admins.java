package com.wip.carrental.model;


import org.springframework.lang.NonNull;

import javax.persistence.*;


@Entity
@Table(name = "admins")
public class Admins {
    
    @Column
    @NonNull
    private int adminEmpId;

    @Column
    private String adminAddress;
    @Column
    @NonNull
    private String adminName;
   
    @Id
    @NonNull
    private String adminEmailId;
    
    @Column
    @NonNull
    private String adminPassword;
   

    public int getadminEmpId() {
        return adminEmpId;
    }

    public void setadminEmpId(int adminEmpId) {
        this.adminEmpId = adminEmpId;
    }
    
    
    public String getadminAddress() {
        return adminAddress;
    }

    public void setadminAddress(String aAddress) {
        this.adminAddress = adminAddress;
    }
    
    public String getadminName() {
        return adminName;
    }

    public void setadminName(String adminName) {
        this.adminName = adminName;
    }

    public String getadminEmailId() {
        return adminEmailId;
    }

    public void setadminEmailId(String adminEmailId) {
        this.adminEmailId = adminEmailId;
    }

    public String getadminPassword() {
        return adminPassword;
    }

    public void setadminPassword(String aPassword) {
        this.adminPassword = adminPassword;
    }


}
