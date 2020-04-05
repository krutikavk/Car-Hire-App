package com.wip.carrental.controller;

import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.repository.AdminsRepository;
import com.wip.carrental.model.Admins;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class AdminsController {
	 @Autowired
	    private AdminsRepository adminsRepository;


	    @GetMapping("/admins/{id}")
	    public Optional<Admins> getDriverById(@PathVariable String id) {
	        return adminsRepository.findById(id);
	    }


}
