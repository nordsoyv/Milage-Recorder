package com.visma.home.milagerecorder.db;

import java.util.Date;

public class MilageRecord {
	private int id;
	private Date dato;
	private float distance;
	private float liters;

	public MilageRecord(){
		id = -1;
	}
	
	public MilageRecord(int id, Date dato, float distance, float liters){
		this.id = id;
		this.dato = dato;
		this.distance = distance;
		this.liters = liters;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDato() {
		return dato;
	}

	public void setDato(Date dato) {
		this.dato = dato;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getLiters() {
		return liters;
	}

	public void setLiters(float liters) {
		this.liters = liters;
	}

}
