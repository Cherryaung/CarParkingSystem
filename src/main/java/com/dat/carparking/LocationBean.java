package com.dat.carparking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.dat.carparking.model.Location;
import com.dat.carparking.service.LocationService;

@ManagedBean
@SessionScoped
public class LocationBean implements Serializable{
	public String building_name;
	public String floor_name;
	public String slot_name;
	
    public Location location= new Location();
    private List<String> floors;
    @ManagedProperty(value="#{locationService}")
    LocationService locationService;
    
//Getters and Setters
    public String getSlot_name() {
		return slot_name;
	}

	public void setSlot_name(String slot_name) {
		this.slot_name = slot_name;
	}

    
    public List<String> getFloors() {
		return floors;
	}

	
	public void setFloors(List<String> floors) {
		this.floors = floors;
	}

	

   public String getFloor_name() {
		return floor_name;
	}

	public void setFloor_name(String floor_name) {
		this.floor_name = floor_name;
	}

public LocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

  public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

   public String getBuilding_name() {
		return building_name;
	}

	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	List<Location> location_list = new ArrayList <>();
		
public List<Location> getLocation_list() {
		return location_list;
	}

	public void setLocation_list(List<Location> location_list) {
		this.location_list = location_list;
	}

	//add floorlist to dropdown
	public String floorList() {
		System.out.println("floorlist");
		floors=new LinkedList();
		int count=Integer.parseInt(location.getFloor_name());
		for(int i=1;i<=count;i++) {
			floors.add("Floor"+i);}
		return "newbuilding";
	}
	
	String selectedFloor;
	public void onFloorChange() {  
		System.out.println("Selected floor:"+floor_name);
		if(floor_name !=null && !floor_name.equals("")) { 
			selectedFloor = floor_name; 
		}  
	}
	
	//method screen save
	public String addRecord() {
		  String buildingName=location.getBuilding_name();
		  int slotcount=Integer.parseInt(location.getSlot_name());
		 // System.out.println("Slot Count: "+slotcount);
		  for(int i=1;i<=slotcount;i++) {
			  //location.setBuilding_name(buildingName);
			  //location.setFloor_name(selectedFloor);
		
			  String slot_name = "Slot"+i;
			 // System.out.println(slot_name);
			  //location.setSlot_name(slot_name);
			  location_list.add(new Location(buildingName,selectedFloor,slot_name));
			 // locationService.persistInformation(this.location); 
		      }
		 for(Location l: location_list)
		 {
			 System.out.println("Building name:"+l.getBuilding_name());
			 System.out.println("Floor name:"+l.getFloor_name());
			 System.out.println("Slot name:"+l.getSlot_name());
		 }
		System.out.println("successful");

		return "newbuilding";
	}

	public String persistRecord(Location location) {
		locationService.persistRecord(this.location);
		System.out.println("save");
		return "newbuilding";
	}
	
	
}

