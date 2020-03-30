package com.wip.carrental.model;


import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.lang.NonNull;


@Entity
@Table(name = "vehicles")
public class Vehicles {
	

    @Id
    private int vId ;

    @Column
    private String vName;
    
	@Column
    @NonNull
    private String vType;
    
    @Column
    @NonNull
    private String vLocationCode;
    
    @Column
    @NonNull
    private String vStatus;
    
    @Column
    private String vImageUrl;
    
    @Column(nullable = false)
    private long vBasePrice;
    
    @Column
    private String vReview;
   
//    @Column(nullable = false)
//    private  
//    
//    @Column(nullable = false)
//    private 	
  //  AWSCredentials credentials = new BasicAWSCredentials( "AKIAIVPPCCHO*******",  "1WprFFGrAz3vd6Qb6xixTrD+jb*******");
	
	@Column(nullable = false)
	private TimeZone time;
	
	
    public int getvId() {
		return vId;
	}

	public String getvName() {
		return vName ;
	}

	public String getvType() {
		return vType;
	}

	public String getvLocationCode() {
		return vLocationCode;
	}

	public String getvStatus() {
		return vStatus;
	}

	public String getvImageUrl() {
		return vImageUrl;
	}

	public long getvBasePrice() {
		return vBasePrice;
	}

	public String getvReview() {
		return vReview;
	}

	public TimeZone getTime() {
		return time;
	}

	public void setvId(int vId) {
		this.vId = vId;
	}

	public void setvName(String vName) {
		this.vName = vName;
	}

	public void setvType(String vType) {
		this.vType = vType;
	}

	public void setvLocationCode(String vLocationCode) {
		this.vLocationCode = vLocationCode;
	}

	public void setvStatus(String vStatus) {
		this.vStatus = vStatus;
	}

	public void setvImageUrl(String vImageUrl) {
		this.vImageUrl = vImageUrl;
	}

	public void setvBasePrice(long vBasePrice) {
		this.vBasePrice = vBasePrice;
	}

	public void setvReview(String vReview) {
		this.vReview = vReview;
	}

	public void setTime(TimeZone time) {
		this.time = time;
	}


	
	
	
	  

}
