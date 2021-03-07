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
public class Device {
	@Id
	private Integer deviceId;
    private String description;

	@ManyToMany(mappedBy = "devices", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Tester> testers = new HashSet<>();
	
	public Device() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Device(String description) {
		super();
		this.description = description;
	}

	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Set<Tester> getTesters() {
		return testers;
	}
	public void setTesters(Set<Tester> testers) {
		this.testers = testers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceId == null) ? 0 : deviceId.hashCode());
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
		Device other = (Device) obj;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Devices [deviceId=" + deviceId + ", description=" + description + "]";
	}

    
    
}
