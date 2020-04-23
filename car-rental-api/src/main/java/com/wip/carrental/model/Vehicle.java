package com.wip.carrental.model;

import java.util.List;
import java.util.TimeZone;

import javax.persistence.*;

import org.springframework.lang.NonNull;


@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long vehicleId;

    @Column
    private String vehicleName;
    
	@Column
    @NonNull
	@Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parking_location_id")
	private ParkingLocation parkingLocation;
    
    @Column
    @NonNull
    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;
    
    @Column
    private String vehicleImageUrl;
    
    
    //BasePrice is price per hour
    @Column(nullable = false)
    @NonNull
    private double vehicleBasePrice;
    
    @Column
    private String vehicleReview;
   
//    @Column(nullable = false)
//    private  
//    
//    @Column(nullable = false)
//    private 	
//    AWSCredentials credentials = new BasicAWSCredentials( "AKIAIVPPCCHO*******",  "1WprFFGrAz3vd6Qb6xixTrD+jb*******");
	
	@Column(nullable = false)
	private TimeZone time;
	
	@Column
	private String description;
	
	
	@OneToMany(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "vehicle")
    private List<Reservation> reservation;


	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	@NonNull
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(@NonNull VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	@NonNull
	public ParkingLocation getParkingLocation() {
		return parkingLocation;
	}

	public void setParkingLocation(@NonNull ParkingLocation parkingLocation) {
		this.parkingLocation = parkingLocation;
	}

	@NonNull
	public VehicleStatus getStatus() {
		return vehicleStatus;
	}

	public void setStatus(@NonNull VehicleStatus vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public String getVehicleImageUrl() {
		return vehicleImageUrl;
	}

	public void setVehicleImageUrl(String vehicleImageUrl) {
		this.vehicleImageUrl = vehicleImageUrl;
	}

	public double getVehicleBasePrice() {
		return vehicleBasePrice;
	}

	public void setVehicleBasePrice(double vehicleBasePrice) {
		this.vehicleBasePrice = vehicleBasePrice;
	}

	public String getVehicleReview() {
		return vehicleReview;
	}

	public void setVehicleReview(String vehicleReview) {
		this.vehicleReview = vehicleReview;
	}

	public TimeZone getTime() {
		return time;
	}

	public void setTime(TimeZone time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}
}
