package com.wip.carrental.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "driver_card")
public class DriverCard {
	
	@Id
	private Long dcNumber;
	@ManyToOne
	private int dcLicense;
	private int dcCvv;
	
	private Date dcExpiry;
	
	
}
