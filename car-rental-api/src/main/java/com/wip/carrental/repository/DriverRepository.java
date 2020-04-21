package com.wip.carrental.repository;

import com.wip.carrental.model.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends CrudRepository<Driver, String> {

    Optional<Driver> findFirstByDriverEmailId(String email);
}
