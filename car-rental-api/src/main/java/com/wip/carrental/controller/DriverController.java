package com.wip.carrental.controller;

import com.wip.carrental.model.Driver;
import com.wip.carrental.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PostMapping("/drivers")
    public Driver postDriver(@RequestBody Driver driverObj) {
        driverService.postDriver(driverObj);
        return driverObj;
    }

    @GetMapping("/drivers")
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("/drivers/{id}")
    public Driver getDriverById(@PathVariable String id) {
        Driver driverObj = driverService.getDriverById(id);
        if (driverObj == null) {
            throw new RuntimeException("Driver not found for the Id:" + id);
        }
        return driverObj;
    }

    @DeleteMapping("/drivers/{id}")
    public String deleteDriver(@PathVariable String id) {
        driverService.deleteDriver(id);
        return "Driver has been deleted with id:" + id;
    }
}
