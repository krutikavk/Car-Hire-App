package com.wip.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table
public class DriverCard {

	@Id
	private Long driverCardNumber;

	@Column
	@NonNull
	private int driverCardCvv;

	@Column
	@NonNull
	private Date driverCardExpiry;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Driver driver;

	public Long getDriverCardNumber() {
		return driverCardNumber;
	}

	public void setDriverCardNumber(Long driverCardNumber) {
		this.driverCardNumber = driverCardNumber;
	}

	public int getDriverCardCvv() {
		return driverCardCvv;
	}

	public void setDriverCardCvv(int driverCardCvv) {
		this.driverCardCvv = driverCardCvv;
	}

	@NonNull
	public Date getDriverCardExpiry() {
		return driverCardExpiry;
	}

	public void setDriverCardExpiry(@NonNull Date driverCardExpiry) {
		this.driverCardExpiry = driverCardExpiry;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
}
