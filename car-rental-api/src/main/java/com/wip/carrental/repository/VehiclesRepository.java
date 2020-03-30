package com.wip.carrental.repository;

import com.wip.carrental.model.Vehicles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiclesRepository extends CrudRepository<Vehicles, String> {

}