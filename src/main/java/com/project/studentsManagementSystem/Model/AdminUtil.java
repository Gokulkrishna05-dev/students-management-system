package com.project.studentsManagementSystem.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AdminUtil {
     
     public static void main(String[] args) {
    	 PasswordEncoder passwordencoder=new BCryptPasswordEncoder();
    	 String password="student@pass";
    	 System.out.println(passwordencoder.encode(password));
     }
}
