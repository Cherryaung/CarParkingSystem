package com.dat.carparking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="location")
public class Location {
private int location_id;
private String building_name;
private String floor_name;
private String slot_name;
private String status;

public Location()
{
	}
public Location(String buildingName, String floorName,String slotName)
{
	building_name = buildingName;
	floor_name = floorName;
	slot_name = slotName;
	status="available";
	}
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="location_id")
public int getLocation_id() {
	return location_id;
}
@Column(name="status")
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public void setLocation_id(int location_id) {
	this.location_id = location_id;
}

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

}

