package com.wip.carrental.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.model.Driver;
import com.wip.carrental.model.Reservation;
import com.wip.carrental.model.ReservationStatus;
import com.wip.carrental.model.Vehicle;
import com.wip.carrental.model.VehicleStatus;
import com.wip.carrental.repository.DriverRepository;
import com.wip.carrental.repository.ReservationRepository;
import com.wip.carrental.repository.VehicleRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class ReservationController {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	 
	@GetMapping("/reservations")
    public List<Reservation> getAllReservations() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    @GetMapping("/reservations/{reservationId}")
    public Optional<Reservation> getReservationById(@PathVariable Long reservationId) {
        return reservationRepository.findById(reservationId);
    }
    
    
    //To be tested
    @PostMapping("/reservation")
    public ResponseEntity<?> postReservation(@RequestBody Reservation reservation, @RequestParam(value = "driverEmailId", required = false) String driverEmailId, 
    											@RequestParam(value = "vehicle_id", required = false) Long vehicle_id) {
    	//Driver driver = reservation.getDriver();

    	Driver driver = driverRepository.findById(driverEmailId).orElse(null);
    	Vehicle vehicle = vehicleRepository.findById(vehicle_id).orElse(null);
    	
    	//Check for valid driverEmailId and vehicle_id
    	if( driver == null && vehicle == null) {
    		throw new ResourceNotFoundException("Driver with ID " + driverEmailId + " not found " 
    	                                         + "Vehicle with ID " + vehicle_id + " not found"); 
    	} else if (driver == null ) {
    		throw new ResourceNotFoundException("Driver with ID " + driverEmailId + " not found ");
    	} else if (vehicle == null ) {
    		throw new ResourceNotFoundException("Vehicle with ID " + vehicle_id + " not found");
    	}
    	
    	//Driver and Vehicle both exist--check if teh driver has any existing reservations
    	List<Reservation> reservations = driver.getReservations();
    	
    	for (Reservation r : reservations) {
    	    if(r.getStatus() == ReservationStatus.CURRENT || r.getStatus() == ReservationStatus.UPCOMING) {
    	    	
    	    	//Krutika NEED TO CHANGE RETURN TYPE
    	    	System.out.println("The user already has a reservation. Please rebook after first reservation is completed.");
    	    	return ResponseEntity.ok(reservationRepository.save(reservation));
    	    }
    	}
    	
    	if(vehicle.getStatus() != VehicleStatus.AVAILABLE) {
    		
    		//Krutika NEED TO CHANGE RETURN TYPE 400 maybe
    		System.out.println("The vehicle is already booked. Please rebook with another vehicle.");
    		return ResponseEntity.ok(reservationRepository.save(reservation));
    	}
    		
    	
    	//If everything is green, go ahead and save reservation
    	reservation.setDriver(driver);
    	reservation.setVehicle(vehicle);
    	reservation.setStatus(ReservationStatus.UPCOMING);
        return ResponseEntity.ok(reservationRepository.save(reservation));
    }
    
    
    
    
    //API to cancel a reservation--driver reservation list will be updated, vehicle status changed to Available
    @PutMapping("/reservation/{reservationId}/cancel")
    public ResponseEntity<?> cancelReservation(@PathVariable Long reservationId) {
    	Optional<Reservation> r = reservationRepository.findById(reservationId);
    	
    	if(r.isPresent()) {
    		Reservation reservation = r.get();
    		Vehicle vehicle = reservation.getVehicle();
    		vehicle.setStatus(VehicleStatus.AVAILABLE);
    		reservation.setStatus(ReservationStatus.CANCELLED);
    		return ResponseEntity.ok(reservationRepository.save(reservation));
    	} else {
    		throw new ResourceNotFoundException("Reservation with id = " + reservationId + " not found");
    	}
    	
    }
    
    
    //API to change status of reservation when driver picks vehicle up
    @PutMapping("/reservation/{reservationId}/current")
    public ResponseEntity<?> currentReservation(@PathVariable Long reservationId) {
    	Optional<Reservation> r = reservationRepository.findById(reservationId);
    	
    	if(r.isPresent()) {
    		Reservation reservation = r.get();
    		reservation.setStatus(ReservationStatus.CURRENT);
    		System.out.println("Driver successfully picked up vehicle on reservation" + reservation.getReservationId());
    		return ResponseEntity.ok(reservationRepository.save(reservation));
    	} else {
    		throw new ResourceNotFoundException("Reservation with id = " + reservationId + " not found");
    	}
    	
    }
    
    //API to change status of reservation when driver drops back a vehicle
    @PutMapping("/reservation/{reservationId}/end")
    public ResponseEntity<?> endReservation(@PathVariable Long reservationId) {
    	Optional<Reservation> r = reservationRepository.findById(reservationId);
    	
    	if(r.isPresent()) {
    		Reservation reservation = r.get();
    		reservation.setStatus(ReservationStatus.ENDED);
    		
    		
    		System.out.println("Driver successfully picked up vehicle on reservation" + reservation.getReservationId());
    		return ResponseEntity.ok(reservationRepository.save(reservation));
    	} else {
    		throw new ResourceNotFoundException("Reservation with id = " + reservationId + " not found");
    	}
    	
    }
    
    //Basic Put operation--will probably never be used
    @PutMapping("/reservation/{reservationId}")
    public ResponseEntity<?> updateReservation(@PathVariable Long reservationId, @RequestBody Reservation reservation) {
        return reservationRepository.findById(reservationId).map(newReservation -> {
            newReservation.setDriver(reservation.getDriver());
            newReservation.setVehicle(reservation.getVehicle());
            newReservation.setPickup(reservation.getPickup());
            newReservation.setHours(reservation.getHours());
            newReservation.setPicked(reservation.isPicked());
            newReservation.setPrice();
            newReservation.setStatus(reservation.getStatus());
            return ResponseEntity.ok(reservationRepository.save(reservation));
        }).orElseThrow(() -> new ResourceNotFoundException("Reservation with id = " + reservationId + " not found"));
    }
    
    
    //Krutika Do not test, has bugs: driver reservation list has to be updated, vehicle status has to be changed 
    //Krutika Will probably never be used--write a cancel function instead that changes reservation status
    @DeleteMapping("/reservation/{reservationId}")
	public ResponseEntity<?> deleteReservation(@PathVariable Long reservationId) {
    	
    	//need to add logic to free reserved vehicle up, car returned, review etc
		if (reservationRepository.existsById(reservationId)) {
			reservationRepository.deleteById(reservationId);
			return ResponseEntity.ok("Reservation with " + reservationId + " deleted");
		}
		throw new ResourceNotFoundException("Vehicle with " + reservationId + " not found");

	}    
	 
	
}