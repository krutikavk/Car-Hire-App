package com.wip.carrental.service;

import com.wip.carrental.dao.DriverDao;
import com.wip.carrental.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverDao driverDao;

    @Override
    public List<Driver> getAllDrivers() {
        return driverDao.getAllDrivers();
    }

    @Override
    public Driver getDriverById(String id) {
        return driverDao.getDriverById(id);
    }

    @Override
    public void postDriver(Driver driver) {
        driverDao.postDriver(driver);
    }

    @Override
    public void deleteDriver(String id) {
        driverDao.deleteDriver(id);
    }
}
