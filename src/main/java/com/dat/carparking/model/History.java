package com.dat.carparking.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="history")
public class History {
	private int record_id;
	private String building_name;
	private String floor_name;
	private String slot_name;
	
	 @Column(name="building_name")
	public String getBuilding_name() {
		return building_name;
	}
	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	 @Column(name="floor_name")
	public String getFloor_name() {
		return floor_name;
	}
	public void setFloor_name(String floor_name) {
		this.floor_name = floor_name;
	}
	 @Column(name="slot_name")
	public String getSlot_name() {
		return slot_name;
	}
	public void setSlot_name(String slot_name) {
		this.slot_name = slot_name;
	}
	private String car_number;
	private Time entry_time;
	private Time exit_time;
	private Date parked_date;
	 
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 @Column(name="record_id")
	 public int getRecord_id() {
			return record_id;
		}
		public void setRecord_id(int record_id) {
			this.record_id = record_id;
		}

	@Column(name="car_number")
	public String getCar_number() {
		return car_number;
	}
	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}
	
	@Column(name="entry_time")
	public Time getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Time entry_time) {
		this.entry_time = entry_time;
	}
	
	@Column(name="exit_time")
	public Time getExit_time() {
		return exit_time;
	}
	public void setExit_time(Time exit_time) {
		this.exit_time = exit_time;
	}
	
	@Column(name="parked_date")
	public Date getParked_date() {
		return parked_date;
	}
	public void setParked_date(Date parked_date) {
		this.parked_date = parked_date;
	}
	 
}
