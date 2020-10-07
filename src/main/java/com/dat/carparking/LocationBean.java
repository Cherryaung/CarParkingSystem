package com.dat.carparking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.dat.carparking.model.Admin;
import com.dat.carparking.model.History;
import com.dat.carparking.model.Location;
import com.dat.carparking.model.User;
import com.dat.carparking.service.LocationService;
@ManagedBean
@SessionScoped
public class LocationBean implements Serializable{
	//for properties of location class
	public String building_name;
	public String floor_name;
	public String slot_name;
	public String status;
	//for properties of admin class
	public String admin_name;
	public String admin_password;
	public String new_password;
	
	//for properties of user class
	public String user_name;
	public String user_password;

   
   

	//for new objects
    public Location location= new Location();
    public History h = new History();
   // private Admin admin;
    public User user=new User();
    public Admin admin=new Admin();
     public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public History getH() {
		return h;
	}
	public void setH(History h) {
		this.h = h;
	}
    public List<String> floors=new LinkedList();
    public List<String> buildings=new LinkedList();
    public List<String> slots=new LinkedList();
   
    
	List<Location> location_list = new ArrayList <>();
	 public List<User> accounts = new ArrayList<User>();
    @ManagedProperty(value="#{locationService}")
    LocationService locationService;
    
//Getters and Setters for location's property
    public String getBuilding_name() {
		return building_name;
	}

	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
    
    public String getFloor_name() {
		return floor_name;
	}

	public void setFloor_name(String floor_name) {
		this.floor_name = floor_name;
	}
	
    public String getSlot_name() {
		return slot_name;
	}

	public void setSlot_name(String slot_name) {
		this.slot_name = slot_name;
	}
	public String getStatus()
	{
		return status;
	}
    public void setStatus(String status)
    {
    	this.status = status;
    }

	//Getter and Setter for admin's property
		public String getAdmin_name() {
			return admin_name;
		}
		public void setAdmin_name(String admin_name) {
			this.admin_name = admin_name;
		}
		public String getAdmin_password() {
			return admin_password;
		}
		public void setAdmin_password(String admin_password) {
			this.admin_password = admin_password;
		}
		//Getter and setter for user's property
		public String getUser_name() {
			return user_name;
		}

		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}

		public String getUser_password() {
			return user_password;
		}

		public void setUser_password(String user_password) {
			this.user_password = user_password;
		}
		public String getNew_password() {
			return new_password;
		}


		public void setNew_password(String new_password) {
			this.new_password = new_password;
		}


	//Getter and setter for lists(floor,building,slot)
    public List<String> getFloors() {
		return floors;
	}
	public void setFloors(List<String> floors) {
		this.floors = floors;
	}
	public List<String> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<String> buildings) {
		this.buildings = buildings;
	}
	public List<String> getSlots() {
		return slots;
	}

	public void setSlots(List<String> slots) {
		this.slots = slots;
	}
	
	//Getters and Setters for objects(location, service)
	public Location getLocation() {
			return location;
		}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public void setLocation(Location location) {
			this.location = location;
		}
    public LocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}		
    public List<Location> getLocation_list() {
		return location_list;
	}

	public void setLocation_list(List<Location> location_list) {
		this.location_list = location_list;
	}
	
	//define floor names and insert into floor list (add new building)
	public void floorList() 
	{
		floors.clear();
		String B_Name = location.getBuilding_name();
		Set<String> B_Lists = listBuildings();
		Boolean is_exist = false;
		for(String b: B_Lists)
		{
			if(B_Name.equalsIgnoreCase(b))
			{
				is_exist = true;
				location.setBuilding_name(null);
				break;
			}
		}
		if(is_exist == false)
		{
		  floors=new LinkedList();
		  int count=Integer.parseInt(location.getFloor_name());
			if(count>10) 
			{
				FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(FacesMessage.SEVERITY_ERROR, "More than 10 floors are not allowed!!!!!", "More than 10 floors are not allowed!!!!!"));
			}
			else
			{
			  for(int i=1;i<=count;i++) 
			  {
				floors.add("Floor"+i);
			  }
			}
		}else {
			FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(FacesMessage.SEVERITY_ERROR, "This building name already exist. Please type new building name.", "This building name already exist. Please type new building name."));
		}
		
	}
	
	String selectedFloor;
	public void onFloorChange()
	{  
		System.out.println("Selected floor:"+floor_name);
		if(floor_name !=null && !floor_name.equals("")) 
		{ 
			selectedFloor = floor_name; 
		}  
	}
	
	//method screen save in (add new building)
	public void addRecord() {
		location_list.clear();
		  String buildingName=location.getBuilding_name();
		  if(buildingName!=null && selectedFloor!=null)
		  {
		    int slotcount=Integer.parseInt(location.getSlot_name());
		    for(int i=1;i<=slotcount;i++) 
		     {
			  String slot_name = "Slot"+i;
			  location_list.add(new Location(buildingName,selectedFloor,slot_name));
		     }
		  }
	}
 //save each location record into db (add new building) 
	public String persistRecord() 
	{
		String floor_to_remove="";
	    if(location_list.isEmpty())
	    {
	    	FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "No Record to save", "Building Name already exit"));
	    }else {
		     for(Location l:this.location_list)
		      {
			   floor_to_remove = l.getFloor_name();
			   locationService.persistRecord(l);
		      }
		      location_list.clear();
		      floors.remove(floor_to_remove);
		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Added a New Floor!", ""));
	    }
		return "admin_add_new_building";
	}
	//Cancel to save records
	public String Canceltoaddrecord() {
		location_list.clear();
		return "admin_add_new_building";
	}
	//delete location
	String selected_building;
	public void onBuildingChange()
	{
		System.out.println("Selected Building:"+location.getBuilding_name());
		if(location.getBuilding_name() !=null && !location.getBuilding_name().equals("")) { 
			selected_building = location.getBuilding_name(); 
		}  
	}
	 
	public static <T> Set<T> convertListToSet(List<T> lists) 
    { 
        // create a set from the List 
        return new HashSet<>(lists); 
    } 
	
	public Set<String>listBuildings(){
		buildings = this.locationService.findBuildinglist();
		Set<String> buildingSet = convertListToSet(buildings); 
		for(String b: buildingSet)
		{
			System.out.println(b);
		}
		return buildingSet;
	}
	public String getSelectedFloor() {
		return selectedFloor;
	}

	public void setSelectedFloor(String selectedFloor) {
		this.selectedFloor = selectedFloor;
	}

	public String getSelected_building() {
		return selected_building;
	}

	public String getSelected_slot() {
		return selected_slot;
	}
	public void setSelected_slot(String selected_slot) {
		this.selected_slot = selected_slot;
	}
	public void setSelected_building(String selected_building) {
		this.selected_building = selected_building;
	}

	public List<String>listfloors(){
		System.out.println(selected_building);
		floors = this.locationService.findFloorlist(selected_building);//edit again
		
		return floors;
	}
	String selected_slot;
	public void onslotChange()
	{
		System.out.println("Selected Slot:"+slot_name);
		if(slot_name !=null && !slot_name.equals("")) { 
			selected_slot = slot_name; 
		}  
	}
	public List<String>listslots(){
		slots = this.locationService.findSlotlist(selectedFloor,selected_building);//edit again
		
		return slots;
	}
	
	//delete slot
	public void DeleteSlot()
	{
		
		String statuscheck = locationService.getStatus(selected_building, selectedFloor, selected_slot);
		System.out.println("statuscheck: "+statuscheck);
		if(statuscheck.equalsIgnoreCase("occupied"))
		{
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Sorry,you can not delete this slot now!!, Car Occupied in this slot."));
		}else {
		locationService.DeleteSlot(selected_building,selectedFloor,selected_slot);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Deleted Slot Successfully!!!"));
		}
	}
	//delete floor
	public void DeleteFloor()
	{
        long count = locationService.Countoccupy(selected_building,selectedFloor);	
        System.out.println("Count: "+count);
        if(count==0)
        {
		locationService.DeleteFloor(selected_building,selectedFloor);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Deleted Floor Successfully"));
        }else {
        	String msg;
    		
        	if(count==1)
			{
			 msg = "Sorry,you can not delete this floor now!!"+"There are "+count+" Car occupying in this floor currently!";
			}else {
				msg ="Sorry,you can not delete this floor now!!"+"There are "+count+" Cars occupying in this floor currently!";
			}
        	FacesContext context = FacesContext.getCurrentInstance();
        	context.addMessage(null, new FacesMessage(msg));
        }
	}
	//delete building
	public void DeleteBuilding()
	{
		long count = locationService.CountoccupyForBuilding(selected_building);
		System.out.println(count);
		if(count==0)
		{
			System.out.println("delete");
		locationService.DeleteBuilding(selected_building);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Deleted Buildig Successfully"));
		}else {
			System.out.println("no delete");
			String msg;
		
			if(count==1)
			{
			 msg = "Sorry,you can not delete thid building now!!"+"There are "+count+" Car occupying in this Building currently!";
			}else {
				msg ="Sorry,you can not delete this building now!!"+"There are "+count+" Cars occupying in this Building currently!";
			}
        	FacesContext context = FacesContext.getCurrentInstance();
        	context.addMessage(null, new FacesMessage(msg));
		
		}
	}

	 public String adminlogin()
	 {
	 	List t=locationService.adminLogin(admin.getAdmin_name(), admin.getAdmin_password());
	 	if(t.isEmpty())
	 	{
	 		FacesContext.getCurrentInstance().addMessage("msgLogin", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong User Name or Password.", "Check again!"));
	 		System.out.println("Invalid!");
	 		return "admin_login_page";
	 		 
	 	}else {
	 		FacesContext.getCurrentInstance().addMessage("msgLogin", new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Success!", ""));
	 		System.out.println("Successful!");
	 	      return "admin_home_page";
	 	}
	 	}
	 //return null;
	 public String userlogin()
	 {
	 	List t=locationService.userLogin(user.getUser_name(), user.getUser_password());
	 	if(t.isEmpty())
	 	{
	 		FacesContext.getCurrentInstance().addMessage("msgLogin", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong User Name or Password.", "Check again."));
	 		System.out.println("Invalid!");
	 		return "user_login_page";
	 		 
	 	}else {
	 		FacesContext.getCurrentInstance().addMessage("msgLogin", new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Success!", ""));
	 		System.out.println("Successful!");
	 	      return "user_home_page";
	 	}
	 	}
	 public void newfloorlist()
	 {
		 List<String> existing_floor_list = listfloors();
		 int floor_number_inexistingbuilding = existing_floor_list.size();
		 int count=Integer.parseInt(location.getFloor_name());
		 int total_floor_number = count+floor_number_inexistingbuilding;
		 floors=new LinkedList();
		 for(int i=floor_number_inexistingbuilding+1;i<=total_floor_number;i++)
		 {
			 floors.add("Floor"+i);
		 }
		
	 }
	
	 //for admin home page redirecting pages
	 public String redirectBuildings() {
		 return "admin_view_slot_page";
	 }
	 public String redirectSearchHistory() {
		 return "admin_search_history";
	 }
	 public String redirectDeleteHistory() {
		 return "delete_history_by_date";
	 }
	 public String redirectAddNewBuilding() {
		 return "admin_add_new_building";
	 }
	 public String redirectAddNewFloor() {
		 return "admin_add_new_floor";
	 }
	 public String redirectDeleteBuilding() {
		 return "admin_delete_building";
	 }
	 public String redirectDeleteFloor() {
		 return "admin_delete_floor";
	 }
	 public String redirectDeleteSlot() {
		 return "admin_delete_slot";
	 }
	 public String redirectChangeAdminPassword() {
		 return "admin_change_password";
	 }
	 public String redirectChangeUserPassword() {
		 return "user_change_password";
	 }
	 public String redirectCreateNewUserAccount() {
		 return "admin_create_new_user_account";
	 }
	 public String redirectAdminLoginPage() {
		 return "admin_login_page";
	 }
	 public String redirectSecurityLoginPage() {
		 return "user_login_page";
	 }
	 public String redirectHomePage() {
		 return "system_home_page";
	 }
          public String redirectAdminHomePage() {
		 return "admin_home_page";
	 }
	 public String redirectUserHomePage() {
		 return "user_home_page";
	 }
	// method CRUD
		public void persistAccount() {
				locationService.persistAccount(this.user);
				FacesContext.getCurrentInstance().addMessage("msgLogin", new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!!!", "Create User Account Successfully!"));
		 		System.out.println("Successful!");
				
			}
		public List<User>listAccounts(){
				return this.locationService.listAccounts();
			}
		public String showAccount(User user) {
				this.user=user;
				return "show";
			}
		public String editPassword(User users) {
			this.user= users;
			return "edit";
			
		}
			 
		public String updatePassword() {
	        String user_name=user.getUser_name();
	        String user_password=user.getUser_password();
	        String new_password=this.new_password;
	       
	        List t=locationService.validate(user.getUser_name(), user.getUser_password());
	        if(t.isEmpty())
		 	{
		 		FacesContext.getCurrentInstance().addMessage("msgLogin", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong User Name or Password.", "Check again."));
		 		System.out.println("Invalid!");
		 		return "user_change_password";
		 		 
		 	}else {
		 		 locationService.updatePassword(user_name,user_password,new_password);	 
		 	FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Password Updated Successfuly!"));
					 return "admin_home_page";
		 	}
		 }
	public String updatePasswordAdmin() {
        String admin_name=admin.getAdmin_name();
        String admin_password=admin.getAdmin_password();
        String new_password=this.new_password;
        List t=locationService.validateAdmin(admin.getAdmin_name(), admin.getAdmin_password());
        if(t.isEmpty())
	 	{
	 		FacesContext.getCurrentInstance().addMessage("msgLogin", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong User Name or Password.", "Check again."));
	 		System.out.println("Invalid!");
	 		return "admin_change_password";
	 		 
	 	}else {
        locationService.updatePasswordAdmin(admin_name,admin_password,new_password);
		 FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Password Updated Successfuly!"));
		 return "admin_login_page";
	 	}
	 }
		 //view
		 public String toggleStatus(String fname,String sname)
		 {
			 String status = locationService.getStatus(selected_building,fname,sname);
			 if(status.equalsIgnoreCase("available"))
			 {
				 return "green";
			 }else if(status.equalsIgnoreCase("occupied")){
				 return "red";
			 }else
			 {
				 return "gray";
			 }
		 }
       //Disable View
		 public void SlotDisable(String fname,String sname)
		 {
			 locationService.SlotDisable(selected_building,fname,sname);
		 }
		//Available View
		 public void SlotAvailable(String fname,String sname)
		 {
			 locationService.SlotAvailable(selected_building,fname,sname);
		 }
		//user view
		 public String ClearOrOccupy(String color,String fname,String sname)
		 {
			 location.setBuilding_name(selected_building);
			 location.setFloor_name(fname);
			 location.setSlot_name(sname);
			 if(color.equalsIgnoreCase("green"))
			 {
				 return "user_occupied_car_parking_slot";
			 }else if(color.equalsIgnoreCase("red")){
				 System.out.println("To Find Car Number");
				 h.setCar_number(locationService.getCarNumberForClear(selected_building,fname,sname));
				 return "user_clear_car_parking_slot";
			 }else {
				 location.setSlot_name(sname);
				 RequestContext context = RequestContext.getCurrentInstance();
				 context.execute("PF('gray').show();");
				 return "user_view_page";
			 }
		 }
		//Dynamic Dialog
		 public void DynamicDialog(String toggleColor,String floor,String slot)
		 {
			 System.out.println(toggleColor);
			 if(toggleColor.equalsIgnoreCase("green"))
			 {
				 location.setSlot_name(slot);
				 RequestContext context = RequestContext.getCurrentInstance();
				 context.execute("PF('green').show();"); 
			 }else if(toggleColor.equalsIgnoreCase("red")){
				 location.setSlot_name(slot);
				 h.setCar_number(locationService.getCarNumberForClear(selected_building,floor,slot));
				 h.setEntry_time(locationService.getEntryTimeByCarNumber(h.getCar_number(),selected_building,floor,slot));
				 System.out.println(h.getCar_number());
				 RequestContext context = RequestContext.getCurrentInstance();
				 context.execute("PF('red').show();"); 
			 }else {
				 location.setSlot_name(slot);
				 RequestContext context = RequestContext.getCurrentInstance();
				 context.execute("PF('gray').show();");
			 }

		 }
		//cancel reset input
		 public void resetAdmin() {
		        this.admin_name = null;
		        this.admin_password = null;
		        this.new_password=null;
		    }
		 public void resetUser() {
		        this.user_name = null;
		        this.user_password = null;
		        this.new_password=null;
		    }
}