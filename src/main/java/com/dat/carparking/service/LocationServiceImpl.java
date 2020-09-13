package com.dat.carparking.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dat.carparking.dao.LocationDao;
import com.dat.carparking.model.History;
import com.dat.carparking.model.Location;

@Service("locationService")
public class LocationServiceImpl implements LocationService{

	@Autowired
	LocationDao locationDao;
	
	@Transactional
	@Override
	public void persistInformation(Location location) {
		// TODO Auto-generated method stub
		locationDao.persistInformation(location);
		
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
	
	
	
}
