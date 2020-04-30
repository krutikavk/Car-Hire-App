package com.wip.carrental.controller;

import java.util.List;
import java.util.Optional;

import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wip.carrental.model.ParkingLocation;
import com.wip.carrental.model.Vehicle;
import com.wip.carrental.repository.ParkingLocationRepository;
import com.wip.carrental.repository.VehicleRepository;


@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:8080")
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

	//Need to track parking location capacity here when adding a vehicle
	@PostMapping("/vehicles")
	public ResponseEntity<?> postVehicle(@RequestBody Vehicle vehicle, @RequestParam(value = "parking_location_id") Long parking_location_id) {
		
		ParkingLocation parkingLocation = parkingLocationRepository.findById(parking_location_id).orElse(null);
		
		if(parkingLocation != null ) {
			if(parkingLocation.getCapacity() > parkingLocation.getFilledSpots()) {
				vehicle.setParkingLocation(parkingLocation);
				parkingLocation.setFilledSpots(parkingLocation.getFilledSpots() + 1);
				return ResponseEntity.ok(vehicleRepository.save(vehicle)); 
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parking location is full");
			}
			
		} else {
			throw new ResourceNotFoundException("Parking Location with ID " + parking_location_id + " not found"); 
		}
		
	}


	@PutMapping("/vehicles/{vehicleId}")
	public ResponseEntity<?> updateVehicle(@PathVariable Long vehicleId, @RequestBody Vehicle vehicleRequestBody) {
		return vehicleRepository.findById(vehicleId).map(vehicle -> {
			vehicle.setVehicleName(vehicleRequestBody.getVehicleName());
			vehicle.setDescription(vehicleRequestBody.getDescription());
			vehicle.setVehicleBasePrice(vehicleRequestBody.getVehicleBasePrice());
			vehicle.setVehicleType(vehicleRequestBody.getVehicleType());
			vehicle.setParkingLocation(vehicleRequestBody.getParkingLocation());
			vehicle.setStatus(vehicleRequestBody.getStatus());
			return ResponseEntity.ok(vehicleRepository.save(vehicle));
		}).orElseThrow(() -> new ResourceNotFoundException("Vehicle ID " + vehicleId + " not found"));
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
