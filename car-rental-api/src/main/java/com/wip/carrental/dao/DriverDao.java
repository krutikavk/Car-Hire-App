package com.wip.carrental.dao;

import com.wip.carrental.model.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverDao extends CrudRepository<Driver, String> {

}
