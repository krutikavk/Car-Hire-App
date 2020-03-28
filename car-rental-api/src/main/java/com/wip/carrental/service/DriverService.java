package com.wip.carrental.service;

import com.wip.carrental.model.Driver;

import java.util.List;

public interface DriverService {
    List<Driver> getAllDrivers();

    Driver getDriverById(String id);

    void postDriver(Driver employee);

    void deleteDriver(String id);
}
