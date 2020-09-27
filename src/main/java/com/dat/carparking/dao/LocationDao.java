package com.dat.carparking.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.dat.carparking.model.Admin;
import com.dat.carparking.model.History;
import com.dat.carparking.model.Location;
import com.dat.carparking.model.User;

public interface LocationDao {
	 void persistRecord(Location location);
	 void persistHistory(History history);
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
	 public void persistAccount(User users);
	 void updatePassword(String user_name, String user_password,String new_password);
	 void updatePasswordAdmin(String admin_name, String admin_password,String new_password);
	 public List<User>listAccounts();
	 public Boolean checkBuildingName(String building_name);
	 public Location confirmtosave(Location l);
	 public List<Date> findDateList();
	 public List<History> historylists();
	 public List<String> findAllFloorlist();
	 public String getStatus(String bname,String fname,String sname);
     void changeStatusToOccupy(String bname,String fname,String sname,String status);
     void changeStatusToClear(String bname,String fname,String sname,String status);
     void addExit_Time(String building_name, String floor_name, String slot_name,String car_number,Timestamp exitTime);
     public String getCarNumberForClear(String building_name,String floor_name,String slot_name);
     public Timestamp getEntryTimeByCarNumber(String car_number,String building_name,String floor_name,String slot_name);
   //  public Timestamp getExitTime(String building_name, String floor_name, String slot_name);
     void SlotDisable(String bname,String fname,String sname);
     void SlotAvailable(String bname,String fname,String sname);
}

