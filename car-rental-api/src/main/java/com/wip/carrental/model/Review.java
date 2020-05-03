package com.wip.carrental.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "review")

@ApiModel(description = "All details about the reviews ")
public class Review {
	
	@Id
	@GeneratedValue
	private Integer commitId;
	
	@OneToOne(mappedBy="review")
	private Reservation reservation;
	
	@Column
	private String comment = "Default comment";
	
	@Column
	@Min(value = 1)
	@Max(value = 5)
	private int rating = 5;

	public Integer getCommitId() {
		return commitId;
	}

	public void setCommitId(Integer commitId) {
		this.commitId = commitId;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
