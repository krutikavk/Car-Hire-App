package com.wip.carrental.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
//need to remove
import javax.persistence.ManyToOne;

@Entity
public class DriverCard {
	
	@Id
	private Long dcNumber;
	@ManyToOne(targetEntity = Driver.class)
	private String dcLicense;
	private int dcCvv;
	
	private Date dcExpiry;


}
