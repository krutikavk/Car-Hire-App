package com.wip.carrental.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.model.Reservation;
import com.wip.carrental.model.SearchRequestBody;
import com.wip.carrental.repository.ReservationRepository;
import io.swagger.annotations.Api;
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
@Api(value = "Vehicle Management System")
@CrossOrigin(origins = "http://localhost:3000")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingLocationRepository parkingLocationRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @GetMapping("/vehicles/{vehicleId}")
    public Optional<Vehicle> getAllVehicles(@PathVariable Long vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }


    //Get average reviews for vehicle
    @GetMapping("/vehicles/{vehicleId}/reviews")
    public float getAverageRatingForVehicle(@PathVariable Long vehicleId) {
        Iterable<Reservation> reservation = reservationRepository.findAll();
        float totalRating = 0;
        int countRating = 0;
        while (reservation.iterator().hasNext()) {
            Reservation r = reservation.iterator().next();
            if (r.getVehicle().getVehicleId() == vehicleId) {
                totalRating += r.getReview().getRating();
                countRating++;
            }
        }

        return totalRating / countRating;
    }


    //Need to track parking location capacity here when adding a vehicle
    @PostMapping("/vehicles/{locationid}")
    public ResponseEntity<?> postVehicle(@RequestBody Vehicle vehicle, @PathVariable Long locationid) {

        ParkingLocation parkingLocation = parkingLocationRepository.findById(locationid).orElse(null);

        if (parkingLocation != null) {
            if (parkingLocation.getCapacity() > parkingLocation.getFilledSpots()) {
                vehicle.setParkingLocation(parkingLocation);
                parkingLocation.setFilledSpots(parkingLocation.getFilledSpots() + 1);
                return ResponseEntity.ok(vehicleRepository.save(vehicle));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parking location is full");
            }

        } else {
            throw new ResourceNotFoundException("Parking Location with ID " + locationid + " not found");
        }

    }

    @PostMapping("/vehicles/search")
<<<<<<< HEAD
    public ResponseEntity<?> SearchVehiclesByLocation(@RequestBody ParkingLocation location) {
=======
    public ResponseEntity<?> SearchVehicles(@RequestBody SearchRequestBody location) {
>>>>>>> 2eb415e24b4b807533c4d36c4e4458a71fe7018d

        try {
            ArrayList<ParkingLocation> parkingLocations = (ArrayList<ParkingLocation>) parkingLocationRepository.findAll();

<<<<<<< HEAD
            List<ParkingLocation> cityLocations = parkingLocations.stream().filter(s -> s.getCity().equalsIgnoreCase(location.getCity())).collect(Collectors.toList());
=======
            String requestCity = location.getCity()== null ? "" : location.getCity();
            String type = location.getType() == null ? "" : location.getType();
            List<ParkingLocation> cityLocations = parkingLocations.stream().filter(s -> s.getCity().toLowerCase().contains(requestCity.toLowerCase())).collect(Collectors.toList());
>>>>>>> 2eb415e24b4b807533c4d36c4e4458a71fe7018d

            ArrayList<Vehicle> result = new ArrayList<>();

            if (type.equalsIgnoreCase("sedan") || type.equalsIgnoreCase("truck") || type.equalsIgnoreCase("suv") || type.equalsIgnoreCase("small")) {
                for (ParkingLocation city : cityLocations) {
                    ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleRepository.findAllByParkingLocation(city).orElse(null);

                    if (vehicles != null) {
                        result.addAll(Objects.requireNonNull(vehicles.stream().filter(s -> s.getVehicleType().toString().equalsIgnoreCase(type)).collect(Collectors.toList())));
                    }
                }

                if (result.size() == 0) {
                    for (ParkingLocation city : parkingLocations) {
                        ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleRepository.findAllByParkingLocation(city).orElse(null);

                        if (vehicles != null) {
                            result.addAll(Objects.requireNonNull(vehicles.stream().filter(s -> s.getVehicleType().toString().equalsIgnoreCase(type)).collect(Collectors.toList())));
                        }
                    }
                }
            } else {
                for (ParkingLocation city : cityLocations) {
                    ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleRepository.findAllByParkingLocation(city).orElse(null);

                    if (vehicles != null) {
                        result.addAll(Objects.requireNonNull(vehicles));
                    }
                }
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());

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
            Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
            ParkingLocation parkingLocation = vehicle.getParkingLocation();
            parkingLocation.setFilledSpots(parkingLocation.getFilledSpots() - 1);
            vehicleRepository.deleteById(vehicleId);

            return ResponseEntity.ok("Vehicle with " + vehicleId + " deleted");
        }
        throw new ResourceNotFoundException("Vehicle with " + vehicleId + " not found");

    }
}
