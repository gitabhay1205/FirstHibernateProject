package com.abhay.dto;

import javax.persistence.Entity;

@Entity
public class TwoWheeler extends Vechile {

	private String steeringHandle;

	public String getSteeringHandle() {
		return steeringHandle;
	}

	public void setSteeringHandle(String steeringHandle) {
		this.steeringHandle = steeringHandle;
	}
}
