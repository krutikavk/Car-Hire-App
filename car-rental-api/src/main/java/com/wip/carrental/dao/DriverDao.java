package com.wip.carrental.dao;

import com.wip.carrental.model.Driver;

import java.util.List;

public interface DriverDao {
    List<Driver> getAllDrivers();

    Driver getDriverById(String id);

    void postDriver(Driver employee);

    void deleteDriver(String id);
}
