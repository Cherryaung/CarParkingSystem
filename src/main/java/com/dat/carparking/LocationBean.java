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
import com.dat.carparking.model.Admin;
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
	
	//for properties of admin class
	public String admin_name;
	public String admin_password;
	public String new_password;
	
	//for properties of user class
	public String user_name;
	public String user_password;
	private static final long serialVersionUID = -2132320822029255792L;

	//for new objects
    public Location location= new Location();
    private Admin admin;
    public User user=new User();
    @PostConstruct
    public void init() {
    	admin=new Admin();
    }
     public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
    
    public List<String> floors;
    public List<String> buildings;
    public List<String> slots;
   
    
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
	public String floorList() 
	{
		  System.out.println("floorlist");
		  floors=new LinkedList();
		  int count=Integer.parseInt(location.getFloor_name());
		  for(int i=1;i<=count;i++) 
		  {
			floors.add("Floor"+i);
		  }
		  return "newbuilding";
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
	public String addRecord() {
		  String buildingName=location.getBuilding_name();
		  int slotcount=Integer.parseInt(location.getSlot_name());
		  for(int i=1;i<=slotcount;i++) 
		  {
			  String slot_name = "Slot"+i;
			  location_list.add(new Location(buildingName,selectedFloor,slot_name));
		  }
		 for(Location l: location_list)
		 {
			 System.out.println("Building name:"+l.getBuilding_name());
			 System.out.println("Floor name:"+l.getFloor_name());
			 System.out.println("Slot name:"+l.getSlot_name());
		 }
		System.out.println("successful");

		return "newbuilding";
	}
 //save each location record into db (add new building) 
	public String persistRecord() 
	{
		String floor_to_remove="";
		for(Location l:this.location_list)
		{
			floor_to_remove = l.getFloor_name();
			locationService.persistRecord(l);
		}
		location_list.clear();
		floors.remove(floor_to_remove);
		return "newbuilding";
	}
	//Cancel to save records
	public String Canceltoaddrecord() {
		location_list.clear();
		return "newbuilding";
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

	public Set<String>listfloors(){
		
		floors = this.locationService.findFloorlist(selected_building);
		Set<String> floorSet = convertListToSet(floors);
		return floorSet;
	}
	String selected_slot;
	public void onslotChange()
	{
		System.out.println("Selected Slot:"+slot_name);
		if(slot_name !=null && !slot_name.equals("")) { 
			selected_slot = slot_name; 
		}  
	}
	public Set<String>listslots(){
		slots = this.locationService.findSlotlist(selectedFloor,selected_building);
		System.out.println("Slot list");
		Set<String> slotSet = convertListToSet(slots);
		return slotSet;
	}
	
	//delete slot
	public void DeleteSlot()
	{
		locationService.DeleteSlot(selected_building,selectedFloor,selected_slot);
	}
	//delete floor
	public void DeleteFloor()
	{
		locationService.DeleteFloor(selected_building,selectedFloor);
	}
	//delete building
	public void DeleteBuilding()
	{
		locationService.DeleteBuilding(selected_building);
	}

	 public String login()
	 {
	 	List t=locationService.adminLogin(admin.getAdmin_name(), admin.getAdmin_password());
	 	
	 	
	 	
	 	
	 	if(t.isEmpty())
	 	{
	 		FacesContext.getCurrentInstance().addMessage("msgLogin", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong User Name or Password.", "Wrong User Name or Password."));
	 		System.out.println("Invalid!");
	 		return "admin_login_page";
	 		 
	 	}else {
	 		FacesContext.getCurrentInstance().addMessage("msgLogin", new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Success!", "Login Success!"));
	 		System.out.println("Successful!");
	 	      return "admin_home_page";
	 	}
	 	}
	 //return null;
	 public String login1()
	 {
	 	List t=locationService.userLogin(user.getUser_name(), user.getUser_password());
	 	
	 	
	 	
	 	
	 	if(t.isEmpty())
	 	{
	 		FacesContext.getCurrentInstance().addMessage("msgLogin", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong User Name or Password.", "Wrong User Name or Password."));
	 		System.out.println("Invalid!");
	 		return "admin_login_page";
	 		 
	 	}else {
	 		FacesContext.getCurrentInstance().addMessage("msgLogin", new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Success!", "Login Success!"));
	 		System.out.println("Successful!");
	 	      return "user_home_page";
	 	}
	 	}
	 public String newfloorlist()
	 {
		 Set<String> existing_floor_list = listfloors();
		 int floor_number_inexistingbuilding = existing_floor_list.size();
		 int count=Integer.parseInt(location.getFloor_name());
		 int total_floor_number = count+floor_number_inexistingbuilding;
		 floors=new LinkedList();
		 for(int i=floor_number_inexistingbuilding+1;i<=total_floor_number;i++)
		 {
			 floors.add("floor"+i);
		 }
		 return "admin_add_new_floor";
	 }
	
	 //for admin home page redirecting pages
	 public String redirectBuildings() {
		 return "admin_view_building";
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
		 return "admin_delete_slot";
	 }
	 public String redirectCreateUserAccount() {
		 return "admin_delete_slot";
	 }
	 public String redirectAdminLoginPage() {
		 return "admin_login_page";
	 }
	 public String redirectSecurityLoginPage() {
		 return "user_login_page";
	 }
	// method CRUD
		public String persistAccount() {
				locationService.persistAccount(this.user);
				FacesContext.getCurrentInstance().addMessage("msgLogin", new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Success!", "Create Account Successfully!"));
		 		System.out.println("Successful!");
				return "create_user_account";
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
		        locationService.updatePassword(user_name,user_password,new_password);
				 FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Transaction Updated Successfuly"));
				 return "admin_home_page";
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
			 }else {
				 return "user_clear_car_parking_slot";
			 }
		 }
}