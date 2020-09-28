package com.dat.carparking.service;

import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import com.dat.carparking.model.Admin;
import com.dat.carparking.model.User;
import com.dat.carparking.model.History;
import com.dat.carparking.model.Location;

public interface LocationService {
	 void persistRecord(Location location);
	 public Boolean persistHistory(History history);
	 void deleteRecord(History history);
	 public List<History> listRecords(Date parked_date);
	 public Admin login(String admin_name, String admin_password);
	 public List<String> findBuildinglist();
	 public List<String> findFloorlist(String selected_building);
	 public List<String> findSlotlist(String selectedFloor,String selected_building);
	 void DeleteSlot(String selected_building, String selectedFloor, String selected_slot);
	 void DeleteFloor(String selected_building, String selectedFloor);
	 void DeleteBuilding(String selected_building);
	 public List adminLogin(String admin_name, String admin_password);
	 public List userLogin(String user_name, String user_password);
	 void persistAccount(User users);
	 void updatePassword(String user_name, String user_password,String new_password);
	 void updatePasswordAdmin(String admin_name, String admin_password,String new_password);
	 public List<User>listAccounts();
	 public Boolean checkBuildingName(String building_name);
	 public Location confirmtosave(Location l);
	 public List<Date> findDateList();
	 public List<History> historylists();
	 public List<String> findAllFloorlist();
	 void changeStatusToOccupy(String bname,String fname,String sname,String status);
	 void changeStatusToClear(String bname,String fname,String sname,String status);
	 public String getStatus(String bname,String fname,String sname);
	 void addExit_time(String building_name, String floor_name, String slot_name,String car_number,Timestamp ts);
     public String getCarNumberForClear(String building_name,String floor_name,String slot_name);
     public Timestamp getEntryTimeByCarNumber(String car_number,String building_name,String floor_name,String slot_name);
   //  public Timestamp getExitTime(String building_name, String floor_name, String slot_name);
     void SlotDisable(String bname,String fname,String sname);
     void SlotAvailable(String bname,String fname,String sname);
     public List validate(String user_name, String user_password);
	 public List validateAdmin(String admin_name, String admin_password);
}
