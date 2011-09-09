package com.visma.home.milagerecorder.db;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "records")
public class MilageRecord {
	
	@DatabaseField(generatedId = true)
	private int id;
	
	@DatabaseField
	private long dato;
	
	@DatabaseField
	private float distance;
	
	@DatabaseField
	private float liters;

	public MilageRecord(){
		
	}
	
	public MilageRecord(int id, Date dato, float distance, float liters){
		this.id = id;
		this.dato = dato.getTime();
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
		return new Date(dato);
	}

	public void setDato(Date dato) {
		this.dato = dato.getTime();
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
