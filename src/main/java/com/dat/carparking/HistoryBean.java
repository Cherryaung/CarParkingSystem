package com.dat.carparking;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	public Time entry_time;
	public Time exit_time;
	public Date parked_date;
    	
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
	
}
