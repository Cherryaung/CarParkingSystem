package com.dat.carparking.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dat.carparking.dao.LocationDao;
import com.dat.carparking.model.Admin;
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
	
	
	
}
