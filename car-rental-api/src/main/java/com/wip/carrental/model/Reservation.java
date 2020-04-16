package com.wip.carrental.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation{
	
	@Id
	@GeneratedValue
	private long reservationId;
	

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "dc_license", nullable = false)
	private Driver driver; 
	
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	@Column
	private Date pickup;
	
	@Column
	private int hours;
	
	@Column
	private boolean picked;
	
	@Column
	private float price;
	
	
	

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
	
	
	
	
	
	
}