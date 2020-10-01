package com.test.enrollment.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Dependent {

	@Id
    @GeneratedValue
	private long id;

	private long did;

	private String name;

	private Date dob;

	@ManyToOne
	@JoinColumn(name = "enrolleeId", nullable = false)
	private Enrollee enrollee;

	public long getDid() {
		return did;
	}

	public void setDid(long did) {
		this.did = did;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Enrollee getEnrollee() {
		return enrollee;
	}

	public void setEnrollee(Enrollee enrollee) {
		this.enrollee = enrollee;
	}

}
