package com.project.studentsManagementSystem.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import com.project.studentsManagementSystem.Jwt.JwtToken;
import com.project.studentsManagementSystem.Model.Admin;
import com.project.studentsManagementSystem.Model.Student;
import com.project.studentsManagementSystem.Service.StudentService;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authmanager;
	
	@Autowired
	JwtToken jwttoken;
	
	@Autowired
	StudentService studentservice;
	
	
	@PostMapping("/adminlogin")
	public ResponseEntity<Map> adminlogin(@RequestBody Admin admin) {
		  System.out.println(admin.getNetid()+" "+admin.getPassword());
		try {
			Authentication authentication=authmanager.authenticate(new UsernamePasswordAuthenticationToken(admin.getNetid(),admin.getPassword()));
			User user=(User)authentication.getPrincipal();
			String token=jwttoken.generateToken(user);
			return ResponseEntity.ok(Map.of("token",token));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error","Invalid username or password"));
		}
	}
	
	@PostMapping("/studentlogin")
	public ResponseEntity<Map> studentLogin(@RequestBody Student student) {
		try {
			Authentication authtoken=authmanager.authenticate(
					new UsernamePasswordAuthenticationToken(student.getNetid(),student.getPassword()));
			User user=(User)authtoken.getPrincipal();
			return ResponseEntity.ok(Map.of("student",studentservice.greet(user)));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of("error",e.getMessage()));
		}
		}
}
