package com.wip.carrental.model;


import org.springframework.lang.NonNull;

import javax.persistence.*;


@Entity
@Table(name = "admins")
public class Admin {
    

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int adminEmpId;

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
   


    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmailId() {
        return adminEmailId;
    }

    public void setAdminEmailId(String adminEmailId) {
        this.adminEmailId = adminEmailId;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
