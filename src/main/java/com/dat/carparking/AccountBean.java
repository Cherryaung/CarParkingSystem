package com.dat.carparking;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.dat.carparking.model.Admin;
import com.dat.carparking.service.LocationService;

@ManagedBean
@SessionScoped
public class AccountBean implements Serializable{
public String admin_name;
public String admin_password;
public Admin admin = new Admin();
@ManagedProperty(value="#{locationService}")
LocationService locationService;

public LocationService getLocationService() {
	return locationService;
}
public void setLocationService(LocationService locationService) {
	this.locationService = locationService;
}
public Admin getAdmin() {
	return admin;
}
public void setAdmin(Admin admin) {
	this.admin = admin;
}
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

public String login()
{
	String name = admin.getAdmin_name();
	String pass = admin.getAdmin_password();
	System.out.println("name: "+ name+" pass: "+pass);
	admin = locationService.login(name,pass);
	
	if(admin != null)
	{
		System.out.println("success");
	return "newbuilding";
	}else {
		System.out.println("fail");
		return "admin_login_page";
	}
	}
//return null;
}
