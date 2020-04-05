package com.wip.carrental.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wip.carrental.model.Admins;

@Repository
public interface AdminsRepository extends CrudRepository<Admins, String> {

}
