package com.dat.carparking;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.dat.carparking.model.Admin;
import com.dat.carparking.model.History;
import com.dat.carparking.model.Location;
import com.dat.carparking.service.LocationService;
@ManagedBean
@SessionScoped
public class LocationBean implements Serializable{
	//for properties of location class
	public String building_name;
	public String floor_name;
	public String slot_name;
	
	//for properties of history class
	public int location_id;
	public String car_number;
	public Time entry_time;
	public Time exit_time;
	public Date parked_date;
	
	//for properties of admin class
	public String admin_name;
	public String admin_password;
	
	//for new objects
	public History history=new History();
    public Location location= new Location();
    public Admin admin = new Admin();
    
    public List<String> floors;
    public List<String> buildings;
    public List<String> slots;
   
    
	List<Location> location_list = new ArrayList <>();
    @ManagedProperty(value="#{locationService}")
    LocationService locationService;
    
//Getters and Setters for location's property
    public String getBuilding_name() {
		return building_name;
	}

	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
    
    public String getFloor_name() {
		return floor_name;
	}

	public void setFloor_name(String floor_name) {
		this.floor_name = floor_name;
	}
	
    public String getSlot_name() {
		return slot_name;
	}

	public void setSlot_name(String slot_name) {
		this.slot_name = slot_name;
	}
	
	//Getters and Setters for history's property
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public String getCar_number() {
		return car_number;
	}
	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}
	public Time getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Time entry_time) {
		this.entry_time = entry_time;
	}
	public Time getExit_time() {
		return exit_time;
	}
	public void setExit_time(Time exit_time) {
		this.exit_time = exit_time;
	}
	public Date getParked_date() {
		return parked_date;
	}
	public void setParked_date(Date parked_date) {
		this.parked_date = parked_date;
	}
    
	//Getter and Setter for admin's property
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}

	//Getter and setter for lists(floor,building,slot)
    public List<String> getFloors() {
		return floors;
	}
	public void setFloors(List<String> floors) {
		this.floors = floors;
	}
	public List<String> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<String> buildings) {
		this.buildings = buildings;
	}
	public List<String> getSlots() {
		return slots;
	}

	public void setSlots(List<String> slots) {
		this.slots = slots;
	}
	
	//Getters and Setters for objects(location,history, admin, service)
	public Location getLocation() {
			return location;
		}

	public void setLocation(Location location) {
			this.location = location;
		}
	public History getHistory() {
		return history;
	}
	public void setHistory(History history) {
		this.history = history;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
    public LocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}		
    public List<Location> getLocation_list() {
		return location_list;
	}

	public void setLocation_list(List<Location> location_list) {
		this.location_list = location_list;
	}
	
	//define floor names and insert into floor list (add new building)
	/*
	 * public String AddBuilding() {
	 * 
	 * return "newbuilding"; }
	 */
	public String floorList() {
		  System.out.println("floorlist");
		  floors=new LinkedList();
		  int count=Integer.parseInt(location.getFloor_name());
		  for(int i=1;i<=count;i++) 
		  {
			floors.add("Floor"+i);
			}
		  return "newbuilding";
	}
	
	String selectedFloor;
	public void onFloorChange() {  
		System.out.println("Selected floor:"+floor_name);
		if(floor_name !=null && !floor_name.equals("")) { 
			selectedFloor = floor_name; 
		}  
	}
	
	//method screen save in (add new building)
	public String addRecord() {
		  String buildingName=location.getBuilding_name();
		  int slotcount=Integer.parseInt(location.getSlot_name());
		  for(int i=1;i<=slotcount;i++) 
		  {
			  String slot_name = "Slot"+i;
			  location_list.add(new Location(buildingName,selectedFloor,slot_name));
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
 //save each location record into db (add new building) 
	public String persistRecord() {
		/*
		 * for(Location l: this.location_list) { Location loc =
		 * locationService.confirmtosave(l); if(loc==null) {
		 * locationService.persistRecord(l); System.out.println("save"); }else {
		 * System.out.println("already exit"); } }
		 */
		String floor_to_remove="";
		for(Location l:this.location_list)
		{
			floor_to_remove = l.getFloor_name();
			locationService.persistRecord(l);
		}
		location_list.clear();
		floors.remove(floor_to_remove);
		return "newbuilding";
	}
	//Cancel to save records
	public String Canceltoaddrecord() {
		location_list.clear();
		return "newbuilding";
	}
	//delete location
	String selected_building;
	public void onBuildingChange()
	{
		System.out.println("Selected Building:"+building_name);
		if(building_name !=null && !building_name.equals("")) { 
			selected_building = building_name; 
		}  
	}
	 
	public static <T> Set<T> convertListToSet(List<T> lists) 
    { 
        // create a set from the List 
        return new HashSet<>(lists); 
    } 
	
	public Set<String>listBuildings(){
		buildings = this.locationService.findBuildinglist();
		Set<String> buildingSet = convertListToSet(buildings); 
		
		return buildingSet;
	}
	public String getSelectedFloor() {
		return selectedFloor;
	}

	public void setSelectedFloor(String selectedFloor) {
		this.selectedFloor = selectedFloor;
	}

	public String getSelected_building() {
		return selected_building;
	}

	public void setSelected_building(String selected_building) {
		this.selected_building = selected_building;
	}

	public Set<String>listfloors(){
		
		floors = this.locationService.findFloorlist(selected_building);
		Set<String> floorSet = convertListToSet(floors);
		return floorSet;
	}
	String selected_slot;
	public void onslotChange()
	{
		System.out.println("Selected Slot:"+slot_name);
		if(slot_name !=null && !slot_name.equals("")) { 
			selected_slot = slot_name; 
		}  
	}
	public Set<String>listslots(){
		slots = this.locationService.findSlotlist(selectedFloor,selected_building);
		System.out.println("Slot list");
		Set<String> slotSet = convertListToSet(slots);
		return slotSet;
	}
	
	//delete slot
	public void DeleteSlot()
	{
		locationService.DeleteSlot(selected_building,selectedFloor,selected_slot);
	}
	//delete floor
	public void DeleteFloor()
	{
		locationService.DeleteFloor(selected_building,selectedFloor);
	}
	//delete building
	public void DeleteBuilding()
	{
		locationService.DeleteBuilding(selected_building);
	}
	//delete history records by date
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	 public void deleteRecords() throws ParseException {
		 String date = format.format(history.getParked_date());
		 System.out.println(date);
		 Date parked_date=format.parse(date);
		 System.out.println(parked_date);
		 List<History> records = locationService.listRecords(parked_date);
		 for(History h: records)
		 {
			locationService.deleteRecord(h); 
			System.out.println("delete");
		 }
		  FacesContext context = FacesContext.getCurrentInstance();
		  context.addMessage(null, new FacesMessage("Successfully deleted."));
		 System.out.println("deleted successfully");
	 }
		/*
		 * public String login() { String name = admin.getAdmin_name(); String pass =
		 * admin.getAdmin_password(); System.out.println("name: "+ name+" pass: "+pass);
		 * admin = locationService.login(name,pass);
		 * 
		 * if(admin != null) { System.out.println("success"); return "admin_home_page";
		 * }else { System.out.println("fail"); return "admin_login_page"; } }
		 */
}

