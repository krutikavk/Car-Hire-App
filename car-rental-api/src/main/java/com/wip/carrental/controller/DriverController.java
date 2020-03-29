package com.wip.carrental.controller;

import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.repository.DriverRepository;
import com.wip.carrental.model.Driver;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
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
        driverObj.setdPassword(hashPassword(driverObj.getdPassword()));
        driverObj.setdMembership();
        return ResponseEntity.ok(driverRepository.save(driverObj));
    }

    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    @PostMapping("/drivers/login")
    public ResponseEntity<?> loginDriver(@RequestBody Driver requestObj) {
        Driver driver = driverRepository.findById(requestObj.getdLicense()).orElse(null);
        if (driver != null) {
            if (checkPass(requestObj.getdPassword(),driver.getdPassword())) {
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
            driver.setdAddress(driverRequestBody.getdAddress());
            driver.setdMembershipEnd(driverRequestBody.getdMembershipEnd());
            driver.setdName(driverRequestBody.getdName());
            driver.setdPassword(driverRequestBody.getdPassword());
            return ResponseEntity.ok(driverRepository.save(driver));
        }).orElseThrow(() -> new ResourceNotFoundException("driverLicense " + driverLicense + " not found"));
    }

    @DeleteMapping("/drivers/{driverLicense}")
    public ResponseEntity<?> deleteDriver(@PathVariable String driverLicense) {
        return driverRepository.findById(driverLicense).map(driver -> {
            driverRepository.delete(driver);
            return ResponseEntity.ok("DriverLicense " + driverLicense + " deleted");
        }).orElseThrow(() -> new ResourceNotFoundException("DriverLicense " + driverLicense + " not found"));
    }
}
