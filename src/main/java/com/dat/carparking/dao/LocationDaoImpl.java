package com.dat.carparking.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dat.carparking.model.Location;
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
//		  Session session=this.sessionFactory.getCurrentSession();
			/*
			 * Query query=(Query) session.createQuery(hql);
			 * query.setParameter("parked_date",parked_date); query.executeUpdate();
			 */
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

	
}

