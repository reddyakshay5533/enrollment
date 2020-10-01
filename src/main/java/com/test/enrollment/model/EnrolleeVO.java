package com.test.enrollment.model;

import java.util.List;

public class EnrolleeVO {

	private long id;

	private String name;

	private Boolean activationStatus;

	private String dob;

	private String phonenum;

	private List<DependentVO> dependents;

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

	public Boolean getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(Boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public List<DependentVO> getDependents() {
		return dependents;
	}

	public void setDependents(List<DependentVO> dependents) {
		this.dependents = dependents;
	}

}
