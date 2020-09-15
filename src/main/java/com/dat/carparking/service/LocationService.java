package com.dat.carparking.service;

import java.util.Date;
import java.util.List;

import com.dat.carparking.model.Admin;
import com.dat.carparking.model.History;
import com.dat.carparking.model.Location;

public interface LocationService {
	 void persistRecord(Location location);
	 void deleteRecord(History history);
	 public List<History> listRecords(Date parked_date);
	 public Admin login(String admin_name, String admin_password);
}
