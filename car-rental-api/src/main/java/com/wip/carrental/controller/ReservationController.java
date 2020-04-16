package com.wip.carrental.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.model.DriverCard;
import com.wip.carrental.model.Reservation;
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
    
    /*
    //How to pass DL/vehicle both here?
    @PostMapping("/reservations")
    public ResponseEntity<?> postReservation(@RequestBody Reservation reservation) {
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
            return ResponseEntity.ok(reservationRepository.save(reservation));
        }).orElseThrow(() -> new ResourceNotFoundException("Reservation with id = " + reservationId + " not found"));
    }
    */
    
    
    
	    
	 
	
}
