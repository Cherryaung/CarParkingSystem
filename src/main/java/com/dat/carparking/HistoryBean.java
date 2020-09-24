package com.dat.carparking;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.dat.carparking.model.History;
import com.dat.carparking.service.LocationService;
@ManagedBean
@SessionScoped
@ViewScoped
public class HistoryBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//for properties of history 
	public String building_name;
	public String floor_name;
	public String slot_name;
	public String car_number;
	public Timestamp entry_time;
	public Timestamp exit_time;
	public Date parked_date;
	public List<String> buildings;	
	public List<String> floors;
	//history obj
	public History history=new History();
	
	//service
	 @ManagedProperty(value="#{locationService}")
	    LocationService locationService;
    //Getters and Setters

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

	public String getCar_number() {
		return car_number;
	}

	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}

	public Timestamp getEntry_time() {
		return entry_time;
	}

	public void setEntry_time(Timestamp entry_time) {
		this.entry_time = entry_time;
	}

	public Timestamp getExit_time() {
		return exit_time;
	}

	public void setExit_time(Timestamp exit_time) {
		this.exit_time = exit_time;
	}

	public Date getParked_date() {
		return parked_date;
	}

	public void setParked_date(Date parked_date) {
		this.parked_date = parked_date;
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

	public LocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}
	public List<String> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<String> buildings) {
		this.buildings = buildings;
	}
	public List<String> getFloors() {
		return floors;
	}

	public void setFloors(List<String> floors) {
		this.floors = floors;
	}
	//Delete records by date
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
	 //Search
	private List<History> historylist;
	private List<History> filteredRecords;
	@PostConstruct
    public void init() {
        historylist = locationService.historylists();
    }

	public List<History> getFilteredRecords() {
		return filteredRecords;
	}

	public void setFilteredRecords(List<History> filteredRecords) {
		this.filteredRecords = filteredRecords;
	}

	public List<History> getHistorylist() {
		return historylist;
	}

	public void setHistorylist(List<History> historylist) {
		this.historylist = historylist;
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
	public Set<String>listFloors(){
		floors = this.locationService.findAllFloorlist();
		Set<String> floorSet = convertListToSet(floors); 
		
		return floorSet;
	}
	//method insert data into history table
		 public String persistHistory() {
			 Date date = new Date();  
		        Timestamp ts=new Timestamp(date.getTime());  
		        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
		        System.out.println(formatter.format(ts));
		        history.setEntry_time(ts);
		        history.setParked_date(new Date());
		        locationService.persistHistory(this.history);
		        FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("User Occupied Successfully"));
			return "History";
			 
		 }
}
