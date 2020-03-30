package com.wip.carrental.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.model.DriverCard;
import com.wip.carrental.model.Vehicles;
import com.wip.carrental.repository.VehiclesRepository;



@RestController
@RequestMapping("/api")
public class VehiclesController {
	
	   @Autowired
	    private VehiclesRepository vehiclesRepository;
	
	   @GetMapping("/vehicles")
	    public List<Vehicles> getAllVehicles() {
	        return (List<Vehicles>) vehiclesRepository.findAll();
	    }

	   
//	   @PostMapping("/vehicles/{vId}/")
//	    public Vehicles postDriverCard(@PathVariable int vId, @Valid @RequestBody Vehicles vehicle) {
//	        return vehiclesRepository.findById(vId).map(vehicle -> {
//	            vehicles.setvId(vId);
//	            return VehiclesRepository.save(vehicle);
//	        }).orElseThrow(() -> new ResourceNotFoundException("vehicleId " + vId + " not found"));
//	    }
//	   
	   
	   
	   
	   
	   
	   

}
