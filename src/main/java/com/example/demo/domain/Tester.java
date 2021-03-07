package com.example.demo.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Tester {
	@Id
	private Integer testerId;
	private String firstName;
	private String lastName;
	private String country;
	private String lastLogin;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tester_device", joinColumns = @JoinColumn(name = "tester_testerId"), 
    inverseJoinColumns = @JoinColumn(name = "device_deviceId"))
	private Set<Device> devices = new HashSet<>();
	public Tester() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Tester(String firstName, String lastName, String country) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
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

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testerId == null) ? 0 : testerId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tester other = (Tester) obj;
		if (testerId == null) {
			if (other.testerId != null)
				return false;
		} else if (!testerId.equals(other.testerId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Testers [testerId=" + testerId + ", firstName=" + firstName + ", lastName=" + lastName + ", country="
				+ country + "]";
	}


}
