package com.wip.carrental.controller;

import java.util.List;
import java.util.Optional;

import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wip.carrental.model.ParkingLocation;
import com.wip.carrental.model.Vehicle;
import com.wip.carrental.repository.ParkingLocationRepository;
import com.wip.carrental.repository.VehicleRepository;


@RestController
@RequestMapping("/api")
public class VehicleController {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private ParkingLocationRepository parkingLocationRepository;

	@GetMapping("/vehicles")
	public List<Vehicle> getAllVehicles() {
		return (List<Vehicle>) vehicleRepository.findAll();
	}

	@GetMapping("/vehicles/{vehicleId}")
	public Optional<Vehicle> getAllVehicles(@PathVariable Long vehicleId) {
		return vehicleRepository.findById(vehicleId);
	}

	@PostMapping("/vehicles")
	public ResponseEntity<?> postVehicle(@RequestBody Vehicle vehicle, @RequestParam(value = "parking_location_id", required = false) Long parking_location_id) {
		
		ParkingLocation parkingLocation = parkingLocationRepository.findById(parking_location_id).orElse(null);
		
		if(parkingLocation != null ) {
			vehicle.setParkingLocation(parkingLocation);
			return ResponseEntity.ok(vehicleRepository.save(vehicle));
			
		} else {
			throw new ResourceNotFoundException("Parking Location with ID " + parking_location_id + " not found"); 
		}
		
	}

	@DeleteMapping("vehicles/{vehicleId}")
	public ResponseEntity<?> deleteVehicle(@PathVariable Long vehicleId) {
		if (vehicleRepository.existsById(vehicleId)) {
			vehicleRepository.deleteById(vehicleId);
			return ResponseEntity.ok("Vehicle with " + vehicleId + " deleted");
		}
		throw new ResourceNotFoundException("Vehicle with " + vehicleId + " not found");

	}
}
