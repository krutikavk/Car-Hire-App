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
	@JoinColumn(name = "dc_license", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	@NonNull
	private Driver driver; 
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="vehicle_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	@NonNull
	private Vehicle vehicle;
	
	@Column
	@NonNull
	private Date pickup;
	
	@Column
	@NonNull
	private int hours;
	
	@Column
	@NonNull
	private boolean picked;
	
	@Column
	@NonNull
	private float price;
	
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

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public boolean isPicked() {
		return picked;
	}

	public void setPicked(boolean picked) {
		this.picked = picked;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}
	
	
	
	
	
	
}