package com.app.messdeck.entity;

import javax.persistence.Entity;

@Entity
public class VendorProfile extends AbstractEntity {

	private long unSubscribeThreshold;
	private double penaltyPercentage;

	public long getUnSubscribeThreshold() {
		return unSubscribeThreshold;
	}

	public void setUnSubscribeThreshold(long unSubscribeThreshold) {
		this.unSubscribeThreshold = unSubscribeThreshold;
	}

	public double getPenaltyPercentage() {
		return penaltyPercentage;
	}

	public void setPenaltyPercentage(double penaltyPercentage) {
		this.penaltyPercentage = penaltyPercentage;
	}

}
