package com.dat.carparking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {
private int admin_id;
private String admin_name;
private String admin_password;

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="admin_id")
public int getAdmin_id() {
	return admin_id;
}
public void setAdmin_id(int admin_id) {
	this.admin_id = admin_id;
}

@Column(name="admin_name")
public String getAdmin_name() {
	return admin_name;
}
public void setAdmin_name(String admin_name) {
	this.admin_name = admin_name;
}

@Column(name="admin_password")
public String getAdmin_password() {
	return admin_password;
}
public void setAdmin_password(String admin_password) {
	this.admin_password = admin_password;
}

}
