package com.dat.carparking.service;

import java.util.Date;
import java.util.List;

import com.dat.carparking.model.History;
import com.dat.carparking.model.Location;

public interface LocationService {
	 void persistRecord(Location location);
	 void deleteRecord(History history);
	 public List<History> listRecords(Date parked_date);
}
