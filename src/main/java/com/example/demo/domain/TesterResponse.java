package com.example.demo.domain;

import javax.persistence.Id;

public class TesterResponse implements Comparable {
	@Id
	private Integer testerId;
	private String firstName;
	private String lastName;
	private String country;
	private int experience;
	

	public TesterResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getTesterId() {
		return testerId;
	}

	public void setTesterId(Integer testerId) {
		this.testerId = testerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "TesterResponse [testerId=" + testerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", country=" + country + ", experience=" + experience + "]";
	}
	
	@Override
	public int compareTo(Object compareTester) {
		// TODO Auto-generated method stub
        int compareExp=((TesterResponse)compareTester).getExperience();
        /* For Ascending order*/
        //return this.experience - compareExp;
        return compareExp-this.experience;
	}
}
