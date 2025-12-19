package com.project.studentsManagementSystem.Controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.studentsManagementSystem.Model.Student;
import com.project.studentsManagementSystem.Model.StudentUtil;
import com.project.studentsManagementSystem.Service.EmailService;
import com.project.studentsManagementSystem.Service.StudentService;

@RestController
@RequestMapping("/student")
public class CrudController {
	
	@Autowired
	StudentService studentservice;
	
	
    @PostMapping("/add")
    public ResponseEntity<Map> addstudent(@RequestBody Student student) {
    	try {
    	return ResponseEntity.ok(Map.of("student",studentservice.addstudent(student)));
    	}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(Map.of("error",e.getMessage()));
    	}
    }
    
    @GetMapping("/getall")
    public List<Student> getAll(){
    	return studentservice.getAllStudents();
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<Map> deleteStudent(@RequestParam int regno) {
    	try {
    		studentservice.deleteStudent(regno);
    		return ResponseEntity.ok(Map.of("status","Student removed"));
    	}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error",e.getMessage()));
    	}	
    }
    
    @PatchMapping("/update")
    public ResponseEntity<Map> updateStudent(@RequestParam Long id,@RequestBody Student student)
    {    try {
    	return ResponseEntity.ok(Map.of("student",studentservice.updateStudent(id,student)));
     }catch(Exception e) {
    	 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error",e.getMessage()));
     }
    	
    }
    
    @GetMapping("/search")
    public ResponseEntity<Map> searchStudent(@RequestParam int regno) {
    	 System.err.println(regno);
    	try {
    		return ResponseEntity.ok(Map.of("student",studentservice.searchStudent(regno)));
    	}catch(Exception e) {
    		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error",e.getMessage()));
    	}	
    }
    
    @GetMapping("/getbyid")
    public ResponseEntity<Map> getByid(@RequestParam long id){
    	try {
    		return ResponseEntity.ok(Map.of("student",studentservice.getByid(id)));
    	}catch(Exception e) {
    		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error",e.getMessage()));
    	}
    }
    
}
