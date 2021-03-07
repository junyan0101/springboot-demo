package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bug {
	@Id
	private Integer bugId;	
	private Integer deviceId;
	private Integer testerId;
	public Bug() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bug(Integer bugId, Integer deviceId, Integer testerId) {
		super();
		this.bugId = bugId;
		this.deviceId = deviceId;
		this.testerId = testerId;
	}
	
	public Integer getBugId() {
		return bugId;
	}
	public void setBugId(Integer bugId) {
		this.bugId = bugId;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getTesterId() {
		return testerId;
	}
	public void setTesterId(Integer testerId) {
		this.testerId = testerId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bugId == null) ? 0 : bugId.hashCode());
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
		Bug other = (Bug) obj;
		if (bugId == null) {
			if (other.bugId != null)
				return false;
		} else if (!bugId.equals(other.bugId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Bug [bugId=" + bugId + ", deviceId=" + deviceId + ", testerId=" + testerId + "]";
	}

	
}
