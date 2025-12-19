package com.project.studentsManagementSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
    String netid;
   String password;
   public Long getId() {
	return id;
   }
   public void setId(Long id) {
	this.id = id;
   }
   public String getNetid() {
	return netid;
   }
   public void setNetid(String netid) {
	this.netid = netid;
   }
   public String getPassword() {
	return password;
   }
   public void setPassword(String password) {
	this.password = password;
   }
   public Admin(Long id, String netid, String password) {
	super();
	this.id = id;
	this.netid = netid;
	this.password = password;
   }
   public Admin() {
   }
   
}
