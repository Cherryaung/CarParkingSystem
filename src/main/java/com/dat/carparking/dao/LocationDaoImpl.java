package com.dat.carparking.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dat.carparking.model.Location;
import com.dat.carparking.model.Admin;
import com.dat.carparking.model.History;


@Repository("locationDao")
public class LocationDaoImpl implements LocationDao{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void persistRecord(Location location) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(location);
	}
	@Override
	public List<History> listRecords(Date parked_date) {
		// TODO Auto-generated method stub
		 String hql="from History where parked_date =:parked_date";
		 return (List<History>) sessionFactory.getCurrentSession().createQuery(hql)
				    .setParameter("parked_date", parked_date)
				    .list();
	}
	@Override
	public void deleteRecord(History history) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(history);
	}
	@Override
	public Admin login(String admin_name, String admin_password) {
		// TODO Auto-generated method stub
		 String sql ="FROM Admin WHERE admin_name=:admin_name AND admin_password=:admin_password";
		Admin admin = (Admin) sessionFactory.openSession().createQuery(sql).setParameter("admin_name",admin_name).setParameter("admin_password", admin_password).uniqueResult();
	  return admin;
	}
	@Override
	public List<String> findBuildinglist() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession(); 
		List<String> building = session.createQuery("SELECT building_name FROM Location").list();
		for(String p : building){ 
			
		}
		return building;
	
	}
	@Override
	public List<String> findFloorlist(String selected_building) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession(); 
		List<String> floor = session.createQuery("SELECT floor_name FROM Location WHERE building_name=:building_name").setParameter("building_name", selected_building).list();
		for(String p : floor){ 
			
		}
		return floor;
	
	}
	@Override
	public List<String> findSlotlist(String selectedFloor,String selected_building) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<String> slot = session.createQuery("SELECT slot_name FROM Location WHERE building_name=:building_name AND floor_name=:floor_name").setParameter("building_name", selected_building).setParameter("floor_name",selectedFloor).list();
        for(String p : slot){ 
			System.out.println(p);
		}
		return slot;
	}
	@Override
	public void DeleteSlot(String selected_building, String selectedFloor, String selected_slot) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		String sql ="FROM Location WHERE building_name=:building_name AND floor_name=:floor_name AND slot_name=:slot_name";
		Location location = (Location) session.createQuery(sql).setParameter("building_name",selected_building).setParameter("floor_name",selectedFloor).setParameter("slot_name",selected_slot).uniqueResult();
		sessionFactory.getCurrentSession().delete(location);
	}
	@Override
	public void DeleteFloor(String selected_building, String selectedFloor) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		String sql ="FROM Location WHERE building_name=:building_name AND floor_name=:floor_name";
	List<Location>location = session.createQuery(sql).setParameter("building_name",selected_building).setParameter("floor_name",selectedFloor).list();
	for(Location l : location)	
	{
	sessionFactory.getCurrentSession().delete(l);}
	}
	@Override
	public void DeleteBuilding(String selected_building) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		String sql="FROM Location WHERE building_name=:building_name";
		List<Location> location = session.createQuery(sql).setParameter("building_name",selected_building).list();
		for(Location l : location)	
		{
		sessionFactory.getCurrentSession().delete(l);}
	}

	
}

