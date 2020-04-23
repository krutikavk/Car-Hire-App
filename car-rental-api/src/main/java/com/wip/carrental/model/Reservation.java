package com.wip.carrental.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reservation")
public class Reservation{
	
	@Id
	@GeneratedValue
	private long reservationId;
	

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "driverEmailId", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Driver driver; 
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="vehicle_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Vehicle vehicle;
	
	@Column
	@NonNull
	private Date pickup;
	
	@Column
	@NonNull
	@Max(value = 72)
	private Integer hours;
	
	@Column
	@NonNull
	private boolean picked;
	
	@Column
	@NonNull
	private double price;
	
	@Column
	@NonNull
	private ReservationStatus status;
	
	

	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}

	
	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	

	public Date getPickup() {
		return pickup;
	}

	public void setPickup(Date pickup) {
		this.pickup = pickup;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public boolean isPicked() {
		return picked;
	}

	public void setPicked(boolean picked) {
		this.picked = picked;
	}

	public double getPrice() {
		return price;
	}

	//Varying price wrt hours booked
	public void setPrice() {
		double basePrice = this.getVehicle().getVehicleBasePrice();
		int hours = this.getHours();
		
		this.price = 0; 
		
		//BasePrice will be price per hour, reduced by 1 for every 8 hours booked
		for( int i = 1; i <= hours; i += 8) {
			this.price += basePrice * 8;
			basePrice -= 1;
		}
		
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}
	
	
}