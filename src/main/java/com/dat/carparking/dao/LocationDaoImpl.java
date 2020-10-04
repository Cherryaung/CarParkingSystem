package com.dat.carparking.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//import javax.persistence.Query;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dat.carparking.model.Location;
import com.dat.carparking.model.User;
import com.dat.carparking.model.Admin;
import com.dat.carparking.model.History;


@Repository("locationDao")
public class LocationDaoImpl implements LocationDao{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void persistRecord(Location location) {
		// TODO Auto-generated method stub
	//	Session session = this.sessionFactory.getCurrentSession();
		/*String sql = "FROM Location WHERE building_name=:building_name AND floor_name=:floor_name AND slot_name=:slot_name";
		Location l = new Location();
		l = (Location)  sessionFactory.openSession().createQuery(sql).setParameter("building_name",location.getBuilding_name()).setParameter("floor_name",location.getFloor_name()).setParameter("slot_name",location.getSlot_name()).uniqueResult();
	    if(l==null) {*/
		sessionFactory.getCurrentSession().save(location);
		/*
															 * }else { System.out.println("already exist"); }
															 */
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
	public void deleteRecord(Date started_date,Date ended_date) {
		// TODO Auto-generated method stub
	   Session session = this.sessionFactory.getCurrentSession();
	   String sql = "DELETE FROM History WHERE parked_date>=:started_date AND parked_date<=:ended_date";
	   Query query = (Query) session.createQuery(sql);
	   query.setParameter("started_date",started_date);
	   query.setParameter("ended_date",ended_date);
	   query.executeUpdate();
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
			System.out.println(p);
		}
		return building;
	
	}
	@Override
	public List<String> findFloorlist(String selected_building) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession(); 
		String sql = "SELECT floor_name FROM Location WHERE building_name=:building_name";
		Query query = (Query) session.createQuery(sql);
		query.setParameter("building_name", selected_building);
		List<String> floorList= new LinkedList(new LinkedHashSet(query.list()));
		return floorList;
	}
	@Override
	public List<String> findSlotlist(String selectedFloor,String selected_building) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "SELECT slot_name FROM Location WHERE building_name=:building_name AND floor_name=:floor_name ORDER BY location_id ASC";
		Query query  = (Query) session.createQuery(sql);
		query.setParameter("building_name", selected_building);
		query.setParameter("floor_name", selectedFloor);
		List<String> slotList = new LinkedList(new LinkedHashSet(query.list()));
	    for(String slot:slotList)
	    {
	    	System.out.println(slot);
	    }
		return slotList;
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
	@Override
	public Location confirmtosave(Location l) {
		// TODO Auto-generated method stub
		String sql = "FROM Location WHERE building_name=:building_name AND floor_name=:floor_name AND slot_name=:slot_name";
		Location loc = new Location();
		loc = (Location)  sessionFactory.openSession().createQuery(sql).setParameter("building_name",l.getBuilding_name()).setParameter("floor_name",l.getFloor_name()).setParameter("slot_name",l.getSlot_name()).uniqueResult();
	    System.out.println("confirmtosave");
		return loc;
	}
	@Override
	public Boolean checkBuildingName(String building_name) {
		// TODO Auto-generated method stub
		String sql = "FROM Location WHERE building_name=:building_name";
		List<Location>location =  sessionFactory.openSession().createQuery(sql).setParameter("building_name", building_name).list();
		if(location == null)
		{
			return false;
		}else {
			return true;
		}
		
	}
	@Override
	public List adminLogin(String admin_name, String admin_password) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.getCurrentSession();
		 String sql ="FROM Admin WHERE admin_name=:admin_name AND admin_password=:admin_password";
		Query query = (Query) session.createQuery(sql);
		 query.setParameter("admin_name", admin_name).setParameter("admin_password",admin_password).uniqueResult(); 
		 List result=query.list();
		
		
	  return result;
	}
	
	@Override
	public List userLogin(String user_name, String user_password) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.getCurrentSession();
		 String sql ="FROM User WHERE user_name=:user_name AND user_password=:user_password";
		Query query = (Query) session.createQuery(sql);
		 query.setParameter("user_name", user_name).setParameter("user_password",user_password).uniqueResult(); 
		 List result=query.list();
		
		
	  return result;
	}
	@Override
	public List validate(String user_name, String user_password) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.getCurrentSession();
		 String sql ="FROM User WHERE user_name=:user_name AND user_password=:user_password";
		Query query = (Query) session.createQuery(sql);
		 query.setParameter("user_name", user_name).setParameter("user_password",user_password).uniqueResult(); 
		 List result=query.list();
		
		
	  return result;
	}
	@Override
	public List  validateAdmin(String admin_name, String admin_password) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.getCurrentSession();
		 String sql ="FROM Admin WHERE admin_name=:admin_name AND admin_password=:admin_password";
		Query query = (Query) session.createQuery(sql);
		 query.setParameter("admin_name", admin_name).setParameter("admin_password",admin_password).uniqueResult(); 
		 List result=query.list();
		
		
	  return result;
	}
	@Override
	public List<Date> findDateList() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Date> date_list = session.createQuery("SELECT parked_date FROM History").list();
		return date_list;
	}
	
	@Override
	public List<History> historylists() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<History> history_lists = session.createQuery("FROM History").list();
		
		return history_lists;
	}
	@Override
	public List<String> findAllFloorlist() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<String> floors = session.createQuery("SELECT floor_name FROM Location").list();
		return floors;
	}
	@Override
	public Boolean persistHistory(History history) {
		// TODO Auto-generated method stub
	Session session = this.sessionFactory.getCurrentSession();
	 String sql = "FROM History WHERE car_number=:car_number AND parked_date=:parked_date AND exit_time IS NULL";
	 Query query = (Query) session.createQuery(sql);
	 query.setParameter("car_number",history.getCar_number());
	 query.setParameter("parked_date",history.getParked_date());
	
	 if(query.uniqueResult()==null)
	 {
		 System.out.println("No car number");
		sessionFactory.getCurrentSession().save(history);
		return true;
	 }else {
		 System.out.println("Existing car number");
		 return false;
	 }
															
	}
	@Override
	public void persistAccount(User users) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(users);
	}
	@Override
	public void updatePassword(String user_name, String user_password,String new_password) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.getCurrentSession();
		 String sql ="UPDATE User SET user_password=:new_password WHERE user_name=:user_name and user_password=:user_password";
		 Query query = (Query) session.createQuery(sql);
		 query.setParameter("user_name",user_name);
		 query.setParameter("user_password",user_password);
		 query.setParameter("new_password",new_password);
		 int result=query.executeUpdate();
		 
		
	}
	@Override
	public void updatePasswordAdmin(String admin_name, String admin_password,String new_password) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.getCurrentSession();
		 String sql ="UPDATE Admin SET admin_password=:new_password WHERE admin_name=:admin_name and admin_password=:admin_password";
		 Query query = (Query) session.createQuery(sql);
		 query.setParameter("admin_name",admin_name);
		 query.setParameter("admin_password",admin_password);
		 query.setParameter("new_password",new_password);
		 int result=query.executeUpdate();
		 
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User>listAccounts(){
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession(); 
		List<User> accList = session.createQuery("from User").list();
		for(User p : accList){ 
			
		}
		return accList;
	}
	@Override
	public void changeStatusToOccupy(String bname, String fname, String sname,String status) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		String sql="UPDATE Location SET status=:status WHERE building_name=:building_name AND floor_name=:floor_name AND slot_name=:slot_name";
		Query query = (Query) session.createQuery(sql);
		query.setParameter("building_name", bname);
		query.setParameter("floor_name", fname);
		query.setParameter("slot_name",sname);
		query.setParameter("status", status);
		query.executeUpdate();
	}
	@Override
	public String getStatus(String bname, String fname, String sname) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		String sql="FROM Location WHERE building_name=:building_name AND floor_name=:floor_name AND slot_name=:slot_name";
		Query query = (Query) session.createQuery(sql);
		query.setParameter("building_name", bname);
		query.setParameter("floor_name", fname);
		query.setParameter("slot_name",sname);
		Location l = (Location) query.uniqueResult();
		String status = l.getStatus();
		System.out.println("Status:"+status);
		return status;
	}
	@Override
	public void changeStatusToClear(String bname, String fname, String sname, String status) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		String sql="UPDATE Location SET status=:status WHERE building_name=:building_name AND floor_name=:floor_name AND slot_name=:slot_name";
		Query query = (Query) session.createQuery(sql);
		query.setParameter("building_name", bname);
		query.setParameter("floor_name", fname);
		query.setParameter("slot_name",sname);
		query.setParameter("status", status);
		query.executeUpdate();
	}
	@Override
	public void addExit_Time(String building_name, String floor_name, String slot_name,String car_number,Timestamp exitTime) {
		// TODO Auto-generated method stub
			Session	session=this.sessionFactory.getCurrentSession();
				String hql="update History set exit_time=:exit_time where building_name=:building_name AND floor_name=:floor_name AND slot_name=:slot_name AND car_number=:car_number";
				Query query=session.createQuery(hql);
				query.setParameter("exit_time",exitTime).setParameter("building_name", building_name).setParameter("floor_name", floor_name).setParameter("slot_name", slot_name).setParameter("car_number",car_number);
				query.executeUpdate();
			}
	@Override
	public String getCarNumberForClear(String building_name, String floor_name, String slot_name) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "SELECT car_number From History WHERE building_name=:building_name AND floor_name=:floor_name AND slot_name=:slot_name AND exit_time IS NULL";
		Query query = session.createQuery(hql);
		query.setParameter("building_name",building_name);
		query.setParameter("floor_name", floor_name);
		query.setParameter("slot_name",slot_name);
		
	//	History h = (History) query.uniqueResult();
		//String car_number = h.getCar_number();
		
		return (String)query.uniqueResult();
	}
	@Override
	public Timestamp getEntryTimeByCarNumber(String car_number, String building_name, String floor_name,
			String slot_name) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "SELECT entry_time From History WHERE building_name=:building_name AND floor_name=:floor_name AND slot_name=:slot_name AND car_number=:car_number";
		Query query = session.createQuery(hql);
		query.setParameter("building_name",building_name);
		query.setParameter("floor_name", floor_name);
		query.setParameter("slot_name",slot_name);
		query.setParameter("car_number",car_number);
		return (Timestamp) query.uniqueResult();
	}
	@Override
	public void SlotDisable(String bname, String fname, String sname) {
		// TODO Auto-generated method stub
		String status = "disable";
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "Update Location SET status=:status WHERE building_name=:building_name AND floor_name=:floor_name AND slot_name=:slot_name";
		Query query = session.createQuery(hql);
		query.setParameter("building_name", bname);
		query.setParameter("floor_name", fname);
		query.setParameter("slot_name",sname);
		query.setParameter("status", status);
		query.executeUpdate();
	}
	@Override
	public void SlotAvailable(String bname, String fname, String sname) {
		// TODO Auto-generated method stub
		String status = "available";
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "Update Location SET status=:status WHERE building_name=:building_name AND floor_name=:floor_name AND slot_name=:slot_name";
		Query query = session.createQuery(hql);
		query.setParameter("building_name", bname);
		query.setParameter("floor_name", fname);
		query.setParameter("slot_name",sname);
		query.setParameter("status", status);
		query.executeUpdate();
	}
	@Override
	public long Countoccupy(String bname, String fname) {
		// TODO Auto-generated method stub
		String status = "occupied";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Location where building_name=:building_name AND floor_name=:floor_name AND status=:status");
	    query.setParameter("building_name", bname);
	    query.setParameter("floor_name", fname);
	    query.setParameter("status", status);
	    long count = (long) query.uniqueResult();
		return count;
	}
	@Override
	public long CountoccupyForBuilding(String bname) {
		// TODO Auto-generated method stub
		String status = "occupied";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Location where building_name=:building_name AND status=:status");
	    query.setParameter("building_name", bname);
	    query.setParameter("status", status);
	    long count = (long) query.uniqueResult();
		return count;
	}
	@Override
	public long countRecord(Date started_date, Date ended_date) {
		// TODO Auto-generated method stub
		System.out.println("count record");
		System.out.println("Start Date: "+started_date);
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from History where parked_date>=:started_date AND parked_date<=:ended_date");
		query.setParameter("started_date",started_date);
		query.setParameter("ended_date",ended_date);
		long count = (long) query.uniqueResult();
		return count;
	}
		
	}


