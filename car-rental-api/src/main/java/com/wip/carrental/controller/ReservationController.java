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
import org.springframework.web.bind.annotation.RestController;

import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.model.Driver;
import com.wip.carrental.model.Reservation;
import com.wip.carrental.model.ReservationStatus;
import com.wip.carrental.repository.ReservationRepository;

@RestController
@RequestMapping("/api")
public class ReservationController {
	
	@Autowired
	private ReservationRepository reservationRepository;
	 
	@GetMapping("/reservations")
    public List<Reservation> getAllReservations() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    @GetMapping("/reservations/{reservationId}")
    public Optional<Reservation> getReservationById(@PathVariable Long reservationId) {
        return reservationRepository.findById(reservationId);
    }
    
    
    //How to pass DL/vehicle both here?
    @PostMapping("/reservations")
    public ResponseEntity<?> postReservation(@RequestBody Reservation reservation) {
    	Driver driver = reservation.getDriver();
    	List<Reservation> reservations = driver.getReservations();
    	
    	for (Reservation r : reservations) {
    	    if(r.getStatus() == ReservationStatus.CURRENT || r.getStatus() == ReservationStatus.UPCOMING) {
    	    	
    	    	//NEED TO CHANGE RETURN TYPE
    	    	System.out.println("The user already has a reservation. Please rebook after first reservation is completed.");
    	    	return ResponseEntity.ok(reservationRepository.save(reservation));
    	    }
    	}
    	
        return ResponseEntity.ok(reservationRepository.save(reservation));
    }

    
    
    @PutMapping("/reservations/{reservationId}")
    public ResponseEntity<?> updateReservation(@PathVariable Long reservationId, @RequestBody Reservation reservation) {
        return reservationRepository.findById(reservationId).map(newReservation -> {
            newReservation.setDriver(reservation.getDriver());
            newReservation.setVehicle(reservation.getVehicle());
            newReservation.setPickup(reservation.getPickup());
            newReservation.setHours(reservation.getHours());
            newReservation.setPicked(reservation.isPicked());
            newReservation.setPrice(reservation.getPrice());
            newReservation.setStatus(reservation.getStatus());
            return ResponseEntity.ok(reservationRepository.save(reservation));
        }).orElseThrow(() -> new ResourceNotFoundException("Reservation with id = " + reservationId + " not found"));
    }
    
    
    @PutMapping("/reservations/{reservationId}/cancel")
    public ResponseEntity<?> cancelReservation(@PathVariable Long reservationId) {
    	Optional<Reservation> r = reservationRepository.findById(reservationId);
    	
    	if(r.isPresent()) {
    		Reservation reservation = r.get();
    		reservation.setStatus(ReservationStatus.CANCELLED);
    		return ResponseEntity.ok(reservationRepository.save(reservation));
    	} else {
    		throw new ResourceNotFoundException("Reservation with id = " + reservationId + " not found");
    	}
    	
    }
    
  //Will probably never be used--write a cancel function instead that changes reservation status
    @DeleteMapping("/reservations/{reservationId}")
	public ResponseEntity<?> deleteVehicle(@PathVariable Long reservationId) {
    	
    	//need to add logic to free reserved vehicle up, car returned, review etc
		if (reservationRepository.existsById(reservationId)) {
			reservationRepository.deleteById(reservationId);
			return ResponseEntity.ok("Reservation with " + reservationId + " deleted");
		}
		throw new ResourceNotFoundException("Vehicle with " + reservationId + " not found");

	}    
	 
	
}