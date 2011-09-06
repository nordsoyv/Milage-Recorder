package com.visma.home.milagerecorder.messages;

import java.util.Date;

import com.visma.home.milagerecorder.service.ServiceNames;

public class AddMilageRecordRequest extends AbstractRequest {

	@Override
	public ServiceNames getServiceName() {
		return ServiceNames.ADD_MILAGE_RECORD;
	}

	float liters;
	float kilometers;
	Date date;

	public float getLiters() {
		return liters;
	}

	public void setLiters(float liters) {
		this.liters = liters;
	}

	public float getKilometers() {
		return kilometers;
	}

	public void setKilometers(float kilometers) {
		this.kilometers = kilometers;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
