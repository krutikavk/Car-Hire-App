package com.wip.carrental.controller;

import com.wip.carrental.repository.AdminsRepository;
import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.model.Admins;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class AdminsController {
    @Autowired
    private AdminsRepository adminsRepository;

    // sign up method for admin
    @PostMapping("/admins/signUp")
    public ResponseEntity<?> postAdmin(@RequestBody Admins adminObj) {

        try {
            adminObj.setadminPassword(hashPassword(adminObj.getadminPassword()));
            return ResponseEntity.ok(adminsRepository.save(adminObj));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("email id already exists");
        }
    }

    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    @PostMapping("/admins/login")
    public ResponseEntity<?> loginAdmin(@RequestBody Admins requestObj) {
        Admins admin = adminsRepository.findById(requestObj.getadminEmailId()).orElse(null);
        if (admin != null) {
            if (checkPass(requestObj.getadminPassword(), admin.getadminPassword())) {
                return ResponseEntity.ok(admin);
            }
            return ResponseEntity.status(403).eTag("password is not matching").build();
        }
        return ResponseEntity.notFound().eTag("admin not found").build();
    }

    private boolean checkPass(String plainPassword, String hashedPassword) {
        return (BCrypt.checkpw(plainPassword, hashedPassword));
    }

    @PutMapping("/admins/{adminEmailId}")
    public ResponseEntity<?> updateAdmin(@PathVariable String adminEmailId, @RequestBody Admins adminRequestBody) {
        return adminsRepository.findById(adminEmailId).map(admin -> {
            admin.setadminAddress(adminRequestBody.getadminAddress());
            admin.setadminName(adminRequestBody.getadminName());
            admin.setadminEmpId(adminRequestBody.getadminEmpId());
            admin.setadminPassword(hashPassword(adminRequestBody.getadminPassword()));
            return ResponseEntity.ok(adminsRepository.save(admin));
        }).orElseThrow(() -> new ResourceNotFoundException("Email Id " + adminEmailId + " not found"));
    }
}
