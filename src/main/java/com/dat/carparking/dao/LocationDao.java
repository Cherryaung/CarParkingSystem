package com.dat.carparking.dao;

import java.util.Date;
import java.util.List;

import com.dat.carparking.model.History;
import com.dat.carparking.model.Location;

public interface LocationDao {
	 void persistRecord(Location location);
	 public List<History> listRecords(Date parked_date);
	 void deleteRecord(History history);
}

