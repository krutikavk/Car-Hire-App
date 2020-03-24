package com.wip.carrental.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Calendar;

@Entity
@Table(name = "driver")
public class Driver {
	
	public Driver(String dLicense, String dAddress, String dName, String dEmailId, String dPassword) {
		// TODO Auto-generated constructor stub
		this.dLicense = dLicense;
		this.dAddress = dAddress;
		this.dName = dName;
		this.dEmailId = dEmailId;
		this.dPassword = dPassword;
	}
	
	public String getdLicense() {
		return dLicense;
	}
	
	public void setdLicense(String dLicense) {
		this.dLicense = dLicense;
	}
	
	public String getdAddress() {
		return dAddress;
	}
	
	public void setdAddress(String dAddress) {
		this.dAddress = dAddress;
	}
	public String getdName() {
		return dName;
	}
	
	public void setdName(String dName) {
		this.dName = dName;
	}
	
	public String getdEmailId() {
		return dEmailId;
	}
	
	public void setdEmailId(String dEmailId) {
		this.dEmailId = dEmailId;
	}
	
	public String getdPassword() {
		return dPassword;
	}
	
	public void setdPassword(String dPassword) {
		this.dPassword = dPassword;
	}
	
	public Date getdMembershipStart() {
		return dMembershipStart;
	}
	
	//This will set start and end of the membership
	public void setdMembership() {
		Calendar cal = Calendar.getInstance();
		Date result = cal.getTime();
		this.dMembershipStart = result;
		cal.add(Calendar.MONTH, 6);
		result = cal.getTime();
		this.dMembershipEnd = result;
	}
	
	public Date getdMembershipEnd() {
		return dMembershipEnd;
	}

	
	@Id
	private String dLicense;

	@Column
	private String dAddress;
	@Column
	private String dName;
	@Column
	private String dEmailId;
	@Column
	private String dPassword;
	@Column
	private Date dMembershipStart;
	@Column
	private Date dMembershipEnd;
	
	
	
	
	
}
