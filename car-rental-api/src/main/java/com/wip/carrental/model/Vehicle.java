package com.wip.carrental.model;

import java.util.TimeZone;

import javax.persistence.*;

import org.springframework.lang.NonNull;


@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)

    private long vId ;

    @Column
    private String vName;
    
	@Column
    @NonNull
	@Enumerated(EnumType.STRING)
    private VehicleType vType;

	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parking_location_id")
	private ParkingLocation vLocationCode;
    
    @Column(columnDefinition = "boolean default false")
    @NonNull
    private Boolean vStatus;
    
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
	
	@Column
	private String description;
	
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "vehicle")
    private Reservation reservation;
	
	
	
    public long getvId() {
		return vId;
	}

	public String getvName() {
		return vName ;
	}

	public String getvType() {
		return vType.toString();
	}

	public ParkingLocation getvLocationCode() {
		return vLocationCode;
	}

	public Boolean getvStatus() {
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

	public void setvType(VehicleType vType) {
		this.vType = vType;
	}

	public void setvLocationCode(ParkingLocation vLocationCode) {
		this.vLocationCode = vLocationCode;
	}

	public void setvStatus(Boolean vStatus) {
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
