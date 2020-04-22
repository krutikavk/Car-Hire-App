package com.wip.carrental.controller;

import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.repository.DriverRepository;
import com.wip.carrental.model.Driver;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DriverController {
    @Autowired
    private DriverRepository driverRepository;

    @GetMapping("/drivers")
    public List<Driver> getAllDrivers() {
        return (List<Driver>) driverRepository.findAll();
    }

    @GetMapping("/drivers/{id}")
    public Optional<Driver> getDriverById(@PathVariable String id) {
        return driverRepository.findById(id);
    }

    @PostMapping("/drivers")
    public ResponseEntity<?> postDriver(@RequestBody Driver driverObj) {

        try {
            driverObj.setDriverPassword(hashPassword(driverObj.getDriverPassword()));
            driverObj.setdMembership();

            return ResponseEntity.ok(driverRepository.save(driverObj));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    @PostMapping("/drivers/login")
    public ResponseEntity<?> loginDriver(@RequestBody Driver requestObj) {
        Driver driver = driverRepository.findById(requestObj.getDriverEmailId()).orElse(null);
        if (driver != null) {
            if (checkPass(requestObj.getDriverPassword(),driver.getDriverPassword())) {
                return ResponseEntity.ok(driver);
            }
            return ResponseEntity.status(403).eTag("password is not matching").build();
        }

        return ResponseEntity.notFound().eTag("driver not found").build();
    }

    private boolean checkPass(String plainPassword, String hashedPassword) {
        return (BCrypt.checkpw(plainPassword, hashedPassword));
    }

    @PutMapping("/drivers/{driverLicense}")
    public ResponseEntity<?> updateDriver(@PathVariable String driverLicense, @RequestBody Driver driverRequestBody) {
        return driverRepository.findById(driverLicense).map(driver -> {
            driver.setDriverAddress(driverRequestBody.getDriverAddress());
            driver.setDriverName(driverRequestBody.getDriverName());
            driver.setDriverPassword(hashPassword(driverRequestBody.getDriverPassword()));
            return ResponseEntity.ok(driverRepository.save(driver));
        }).orElseThrow(() -> new ResourceNotFoundException("driverLicense " + driverLicense + " not found"));
    }

    @DeleteMapping("/drivers/{driverEmailId}")
    public ResponseEntity<?> deleteDriver(@PathVariable String driverEmailId) {
        return driverRepository.findById(driverEmailId).map(driver -> {
            driverRepository.delete(driver);
            return ResponseEntity.ok("driverEmailId " + driverEmailId + " deleted");
        }).orElseThrow(() -> new ResourceNotFoundException("driverEmailId " + driverEmailId + " not found"));
    }
}
