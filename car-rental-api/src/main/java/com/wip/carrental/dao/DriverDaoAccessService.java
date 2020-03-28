package com.wip.carrental.dao;

import com.wip.carrental.model.Driver;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DriverDaoAccessService implements DriverDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Driver> getAllDrivers() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Driver> query = currentSession.createQuery("from Driver", Driver.class);
        List<Driver> list = query.getResultList();
        return list;
    }

    @Override
    public Driver getDriverById(String id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Driver driver = currentSession.get(Driver.class, id);
        return driver;
    }

    @Override
    public void postDriver(Driver driver) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(driver);
    }

    @Override
    public void deleteDriver(String id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Driver driver = currentSession.get(Driver.class, id);
        currentSession.delete(driver);
    }
}
