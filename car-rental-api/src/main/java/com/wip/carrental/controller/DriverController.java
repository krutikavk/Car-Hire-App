package com.wip.carrental.controller;

import com.wip.carrental.dao.DriverDao;
import com.wip.carrental.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DriverController {
    @Autowired
    private DriverDao driverDao;

    @PostMapping("/drivers")
    public Driver postDriver(@RequestBody Driver driverObj) {
        driverDao.save(driverObj);
        return driverObj;
    }

    @GetMapping("/drivers")
    public List<Driver> getAllDrivers() {
        return (List<Driver>) driverDao.findAll();
    }

    @GetMapping("/drivers/{id}")
    public Optional<Driver> getDriverById(@PathVariable String id) {
        return driverDao.findById(id);
    }

    @DeleteMapping("/drivers/{id}")
    public String deleteDriver(@PathVariable String id) {
        driverDao.deleteById(id);
        return "Driver has been deleted with id:" + id;
    }
}
