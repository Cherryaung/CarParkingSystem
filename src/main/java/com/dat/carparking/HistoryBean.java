package com.dat.carparking;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;

import com.dat.carparking.model.History;
import com.dat.carparking.model.Location;
import com.dat.carparking.service.LocationService;
@ManagedBean
@SessionScoped
public class HistoryBean implements Serializable{
	@ManagedProperty(value="#{locationService}")
	LocationService locationService;
 public int location_id;
 public String car_number;
 public Time entry_time;
 public Time exit_time;
 public Date parked_date;
 public History history=new History();
public History getHistory() {
	return history;
}
public void setHistory(History history) {
	this.history = history;
}
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
public LocationService getLocationService() {
	return locationService;
}

public void setLocationService(LocationService locationService) {
	this.locationService = locationService;
}

//delete Msg
/*
 * private String msg="Are you sure all records are deleted in ";
 * public String
 * getMsg() { String confirm_date = format.format(history.getParked_date());
 * System.out.println(confirm_date); msg= msg+confirm_date+" ?"; return msg; }
 * public void setMsg(String msg) { msg = this.msg; }
 */
//delete Records
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

}
