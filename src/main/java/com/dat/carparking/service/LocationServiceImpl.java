package com.dat.carparking.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import com.dat.carparking.dao.LocationDao;
import com.dat.carparking.model.Admin;
import com.dat.carparking.model.User;
import com.dat.carparking.model.History;
import com.dat.carparking.model.Location;

@Service("locationService")
public class LocationServiceImpl implements LocationService{

	@Autowired
	LocationDao locationDao;
	
	@Transactional
	@Override
	public void persistRecord(Location location) {
		// TODO Auto-generated method stub
		locationDao.persistRecord(location);
		
	}
	@Transactional
	@Override
	public void persistHistory(History history) {
		// TODO Auto-generated method stub
		locationDao.persistHistory(history);
		
	}
	@Transactional
	@Override
	public List<History> listRecords(Date parked_date){
		return locationDao.listRecords(parked_date);
	}
	@Transactional
	@Override
	public void deleteRecord(History history) {
		// TODO Auto-generated method stub
		locationDao.deleteRecord(history);
	}
	@Transactional
	@Override
	public Admin login(String admin_name, String admin_password) {
		// TODO Auto-generated method stub
		return locationDao.login(admin_name, admin_password);
	}
	@Transactional
	@Override
	public List<String> findBuildinglist() {
		// TODO Auto-generated method stub
		return locationDao.findBuildinglist();
	}
	@Transactional
	@Override
	public List<String> findFloorlist(String selected_building) {
		// TODO Auto-generated method stub
		return locationDao.findFloorlist(selected_building);
	}
	@Transactional
	@Override
	public List<String> findSlotlist(String selectedFloor, String selected_building) {
		// TODO Auto-generated method stub
		return locationDao.findSlotlist(selectedFloor, selected_building);
	}
	@Transactional
	@Override
	public void DeleteSlot(String selected_building, String selectedFloor, String selected_slot) {
		// TODO Auto-generated method stub
		locationDao.DeleteSlot(selected_building, selectedFloor, selected_slot);
	}
	@Transactional
	@Override
	public void DeleteFloor(String selected_building, String selectedFloor) {
		// TODO Auto-generated method stub
		locationDao.DeleteFloor(selected_building, selectedFloor);
	}
	@Transactional
	@Override
	public void DeleteBuilding(String selected_building) {
		// TODO Auto-generated method stub
		locationDao.DeleteBuilding(selected_building);
	}
	@Transactional
	@Override
	public List adminLogin(String admin_name, String admin_password) {
		// TODO Auto-generated method stub
		return locationDao.adminLogin(admin_name, admin_password);
	}
	@Transactional
	@Override
	public List userLogin(String user_name, String user_password) {
		// TODO Auto-generated method stub
		return locationDao.userLogin(user_name, user_password);
	}
	@Transactional
	@Override
	public Boolean checkBuildingName(String building_name) {
		// TODO Auto-generated method stub
		return locationDao.checkBuildingName(building_name);
	}
	@Transactional
	@Override
	public Location confirmtosave(Location l) {
		// TODO Auto-generated method stub
		return locationDao.confirmtosave(l);
	}
	@Transactional
	public List<Date> findDateList() {
		// TODO Auto-generated method stub
		return locationDao.findDateList();
	}
	
	@Transactional
	@Override
	public List<History> historylists() {
		// TODO Auto-generated method stub
		return locationDao.historylists();
	}
	@Transactional
	@Override
	public List<String> findAllFloorlist() {
		// TODO Auto-generated method stub
		return locationDao.findAllFloorlist();
	}
	@Transactional
	@Override
	public void persistAccount(User users){
		// TODO Auto-generated method stub
		locationDao.persistAccount(users);
	}
	@Transactional
	@Override
	public void updatePassword(String user_name, String user_password,String new_password) {
		// TODO Auto-generated method stub
		locationDao.updatePassword(user_name,user_password,new_password);
	}
	@Transactional
	@Override
	public void updatePasswordAdmin(String admin_name, String admin_password,String new_password) {
		// TODO Auto-generated method stub
		locationDao.updatePassword(admin_name,admin_password,new_password);
	}
	@Transactional
	@Override
	public List<User>listAccounts(){
		// TODO Auto-generated method stub
		return this.locationDao.listAccounts();
	}
	@Transactional
	@Override
	public void changeStatusToOccupy(String bname, String fname, String sname,String status) {
		// TODO Auto-generated method stub
		locationDao.changeStatusToOccupy(bname, fname, sname,status);
	}
	@Transactional
	@Override
	public String getStatus(String bname, String fname, String sname) {
		// TODO Auto-generated method stub
		return locationDao.getStatus(bname,fname,sname);
	}
	@Transactional
	@Override
	public void changeStatusToClear(String bname, String fname, String sname, String status) {
		// TODO Auto-generated method stub
		locationDao.changeStatusToClear(bname, fname, sname, status);
	}
	@Transactional
	@Override
	public void addExit_time(String building_name, String floor_name, String slot_name,String car_number,Timestamp ts) {
	// TODO Auto-generated method stub
	locationDao.addExit_Time(building_name, floor_name, slot_name,car_number, ts);
	}
	@Transactional
	@Override
	public String getCarNumberForClear(String building_name, String floor_name, String slot_name) {
		// TODO Auto-generated method stub
		return locationDao.getCarNumberForClear(building_name,floor_name,slot_name);
	}
	@Transactional
	@Override
	public Timestamp getEntryTimeByCarNumber(String car_number, String building_name, String floor_name,
			String slot_name) {
		// TODO Auto-generated method stub
		return locationDao.getEntryTimeByCarNumber(car_number, building_name, floor_name, slot_name);
	}
	/*
	 * @Transactional
	 * 
	 * @Override public Timestamp getExitTime(String building_name, String
	 * floor_name, String slot_name) { // TODO Auto-generated method stub return
	 * locationDao.getExitTime(building_name, floor_name, slot_name); }
	 */
	@Transactional
	@Override
	public void SlotDisable(String bname, String fname, String sname) {
		// TODO Auto-generated method stub
		locationDao.SlotDisable(bname,fname,sname);
	}
	@Transactional
	@Override
	public void SlotAvailable(String bname, String fname, String sname) {
		// TODO Auto-generated method stub
		locationDao.SlotAvailable(bname, fname, sname);
	}
}
