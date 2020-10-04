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
	public String submitted_security;
	public List<String> buildings;	
	public List<String> floors;
	private Date maxDate = new Date();

	public Date getMaxDate() {
	    return maxDate;
	}

	public void setMaxDate(Date maxDate) {
	    this.maxDate = new Date();
	}
	public Date start_date;
	public Date end_date;
	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
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
    
	public String getSubmitted_security() {
		return submitted_security;
	}

	public void setSubmitted_security(String submitted_security) {
		this.submitted_security = submitted_security;
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
				System.out.println("delete method is invoked");
		     System.out.println("Start Date:"+start_date);
		     System.out.println("End Date:"+end_date);
			 String start_date_string = format.format(start_date);
			 Date started_date = format.parse(start_date_string);
			 System.out.println("Started date: "+start_date_string);
			 String end_date_string= format.format(end_date);
			  Date ended_date = format.parse(end_date_string);
			 System.out.println("Ended date: "+end_date_string);
			 long count = locationService.countRecord(started_date,ended_date);
			 Date tDay = new Date();
			 String tDayString = format.format(tDay);
			 System.out.println("Today:"+tDay);
			 if(count==0)
			 {
				 FacesContext context = FacesContext.getCurrentInstance();
				 context.addMessage(null, new FacesMessage("No Record to delete in this duration"));
			 }else{
				 if(start_date_string.compareTo(tDayString)==0 || end_date_string.compareTo(tDayString)==0)
				 {
					 FacesContext context = FacesContext.getCurrentInstance();
					 context.addMessage(null, new FacesMessage("You cannot delete records in this day."));
				 }else {
					 locationService.deleteRecord(started_date,ended_date);
					 FacesContext context = FacesContext.getCurrentInstance();
					 context.addMessage(null, new FacesMessage("Deleted Record Successfully"));
				 }
				 }
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
	 public String persistHistory(String bname,String fname,String sname,String security) {
		 System.out.println("Building Name:"+bname);
		 System.out.println("Floor Name:"+fname);
		 System.out.println("Slot Name:"+sname);
		 String status = "occupied";
		 Date date = new Date();  
	        Timestamp ts=new Timestamp(date.getTime());  
	        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
	        System.out.println(formatter.format(ts));
	        History h = new History();
	        h.setBuilding_name(bname);
	        h.setFloor_name(fname);
	        h.setSlot_name(sname);
	        h.setEntry_time(ts);
	        h.setCar_number(history.getCar_number());
	        h.setParked_date(new Date());
	        h.setSubmitted_security(security);
	     Boolean Is_Save=locationService.persistHistory(h);
	     if(Is_Save == true)
	     {
	    	 System.out.println("Save OK");
	        locationService.changeStatusToOccupy(bname,fname,sname,status);
	        FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("User Occupied Car Parking Slot Successfully"));
	     }else {
	    	 System.out.println("Save not OK");
	    	 FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(FacesMessage.SEVERITY_ERROR, "The Car number already existed in another slot. Please check car number again!!", "The Car number already existed in another slot. Please check car number again!!"));
	     }
		return "History";
		 
	 }
		 //method user clear carparking slot 
		 public void updateHistory(String bname,String fname,String sname,String car_number) {
			 
			 String status = "available";
			 Date date = new Date();  
			  Timestamp ts=new Timestamp(date.getTime());
			  locationService.addExit_time(bname,fname,sname,car_number,ts);
			  locationService.changeStatusToClear(bname,fname,sname,status);
			  System.out.println(" clear slot Buidling "+history.getBuilding_name()+" floor_name "+floor_name+" slot_name "+slot_name);
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("User Clear Car Parking Slot Successfully"));
			  
		}
		 public String computeDuration(Timestamp entry,Timestamp exit)
		 { 
			 String duration;
			 if(exit!=null)
			 {
            long milliseconds = exit.getTime()-entry.getTime();
            int seconds = (int) milliseconds/1000;
            int hours = seconds/3600;
            int minutes = (seconds%3600)/60;
            seconds = (seconds%3600)%60;
             duration= hours+":"+minutes+":"+seconds;
            System.out.println(duration);
            return duration;}else {
            	duration=null;
            		return duration;
            }
		 }
}
