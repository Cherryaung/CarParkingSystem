package com.dat.carparking.dao;

import java.util.Date;
import java.util.List;

import com.dat.carparking.model.Admin;
import com.dat.carparking.model.History;
import com.dat.carparking.model.Location;

public interface LocationDao {
	 void persistRecord(Location location);
	 public List<History> listRecords(Date parked_date);
	 void deleteRecord(History history);
	 public Admin login(String admin_name, String admin_password);
	 void DeleteSlot(String selected_building, String selectedFloor, String selected_slot);
	 void DeleteFloor(String selected_building, String selectedFloor);
	 void DeleteBuilding(String selected_building);
	 public List<String> findBuildinglist();
	 public List<String> findFloorlist(String selected_building);
	 public List<String> findSlotlist(String selectedFloor,String selected_building);
	 public List adminLogin(String admin_name, String admin_password);
	 public List userLogin(String user_name, String user_password);
	 public Boolean checkBuildingName(String building_name);
	 public Location confirmtosave(Location l);
	 public List<Date> findDateList();
	 public List<History> historylists();
	 public List<String> findAllFloorlist();
}

