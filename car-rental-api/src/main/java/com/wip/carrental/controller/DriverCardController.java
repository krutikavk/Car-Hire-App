package com.wip.carrental.controller;


import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.model.Driver;
import com.wip.carrental.repository.DriverCardRepository;
import com.wip.carrental.repository.DriverRepository;
import com.wip.carrental.model.DriverCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class DriverCardController {
    @Autowired
    private DriverCardRepository driverCardRepository;

    @Autowired
    private DriverRepository driverRepository;

    @GetMapping("/drivers/{driverLicense}/cards")
    public Optional<List<DriverCard>> getDriverCardsByLicense(@PathVariable String driverLicense) {
        return driverRepository.findById(driverLicense).map(driver ->
                driverCardRepository.findDriverCardsByDriver(driver)
        );
    }

    @PostMapping("/drivers/{driverLicense}/cards")
    public DriverCard postDriverCard(@PathVariable String driverLicense, @Valid @RequestBody DriverCard driverCard) {
        return driverRepository.findById(driverLicense).map(driver -> {
            driverCard.setDriver(driver);
            return driverCardRepository.save(driverCard);
        }).orElseThrow(() -> new ResourceNotFoundException("driverLicense " + driverLicense + " not found"));
    }

    @DeleteMapping("/drivers/{driverLicense}/cards/{cardNumber}")
    public ResponseEntity<?> deleteDriverCard(@PathVariable String driverLicense, @PathVariable Long cardNumber) {

        Driver driver = driverRepository.findById(driverLicense).orElse(null);
        if (driver != null) {
            DriverCard card = driverCardRepository.findByDriverAndDcNumber(driver, cardNumber).orElse(null);
            if (card != null) {
                driverCardRepository.delete(card);
                return ResponseEntity.ok("driverLicense " + driverLicense + "cardNumber " + cardNumber + " deleted");
            }
        }
        throw new ResourceNotFoundException("driverLicense " + driverLicense + "cardNumber " + cardNumber + " not found");
    }
}
