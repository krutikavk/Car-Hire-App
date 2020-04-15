package com.wip.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "driver_card")
public class DriverCard {
	
	@Id
	private Long dcNumber;

	@Column
	@NonNull
	private int dcCvv;

	@Column
	@NonNull
	private Date dcExpiry;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "dc_license_d_license", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Driver driver;

	public Long getDcNumber() {
		return dcNumber;
	}

	public void setDcNumber(Long dcNumber) {
		this.dcNumber = dcNumber;
	}

	public int getDcCvv() {
		return dcCvv;
	}

	public void setDcCvv(int dcCvv) {
		this.dcCvv = dcCvv;
	}

	public Date getDcExpiry() {
		return dcExpiry;
	}

	public void setDcExpiry(Date dcExpiry) {
		this.dcExpiry = dcExpiry;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}



}
