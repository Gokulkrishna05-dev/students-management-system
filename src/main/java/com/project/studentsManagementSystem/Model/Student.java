package com.project.studentsManagementSystem.Model;

import java.util.Date;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Students")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id;
  String firstname;
  String lastname;
  @Column(nullable = false,unique = true)
  int regno;
  Date dob;
  String gmail;
  String department;
  String specialization;
  @Column(updatable = false , unique=true)
  String netid;
  @Column(updatable = false , unique=true)
  String password;
  String address;
  public Student(long id, String firstname, String lastname, int regno, Date dob, String gmail, String department,
		String specialization, String netid, String password,String address) {
	super();
	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.regno = regno;
	this.dob = dob;
	this.gmail = gmail;
	this.department = department;
	this.specialization = specialization;
	this.netid = netid;
	this.password = password;
	this.address=address;
  }
  public String getAddress() {
	return address;
}
  public void setAddress(String address) {
	this.address = address;
  }
  public void setId(Long id) {
	this.id = id;
  }
  public long getId() {
	return id;
  }
  public String getFirstname() {
	return firstname;
  }
  public void setFirstname(String firstname) {
	this.firstname = firstname;
  }
  public String getLastname() {
	return lastname;
  }
  public void setLastname(String lastname) {
	this.lastname = lastname;
  }
  public int getRegno() {
	return regno;
  }
  public void setRegno(int regno) {
	this.regno = regno;
  }
  public Date getDob() {
	return dob;
  }
  public void setDob(Date dob) {
	this.dob = dob;
  }
  public String getGmail() {
	return gmail;
  }
  public void setGmail(String gmail) {
	this.gmail = gmail;
  }
  public String getDepartment() {
	return department;
  }
  public void setDepartment(String department) {
	this.department = department;
  }
  public String getSpecialization() {
	return specialization;
  }
  public void setSpecialization(String specialization) {
	this.specialization = specialization;
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
  public Student() {
	super();
  }
  public Student(Long id, String firstname, String lastname, int regno, Date dob, String gmail, String department,
		String specialization, String address) {
	super();
	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.regno = regno;
	this.dob = dob;
	this.gmail = gmail;
	this.department = department;
	this.specialization = specialization;
	this.address = address;
  }
  
}
