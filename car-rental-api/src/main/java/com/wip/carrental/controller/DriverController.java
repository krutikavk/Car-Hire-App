package com.wip.carrental.controller;

import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.repository.DriverRepository;
import com.wip.carrental.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Driver getDriverById(@PathVariable String id) {
        return driverRepository.findById(id).orElse(null);
    }

    @PostMapping("/drivers")
    public ResponseEntity<?> postDriver(@RequestBody Driver driverObj) {
        return ResponseEntity.ok(driverRepository.save(driverObj));
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
