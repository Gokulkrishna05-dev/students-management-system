package com.project.studentsManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.studentsManagementSystem.Model.Student;
import com.project.studentsManagementSystem.Model.StudentUtil;
import com.project.studentsManagementSystem.Repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentrepository;
	
	@Autowired
	PasswordEncoder pass;
	
	@Autowired
	EmailService emailservice;
    
	
	public Student addstudent(Student student) throws Exception{
		Student student2=studentrepository.findByregno(student.getRegno());
        if(student2==null) {
        	student.setNetid(StudentUtil.netidGenerator(student));
	    	student.setPassword(StudentUtil.passwordGenerator());
	    	emailservice.sendEmail(student);
	    	student.setPassword(pass.encode(student.getPassword()));
			return studentrepository.save(student);	
        }
        else {
        	throw new Exception("Already a student registered with this register number");
        }
			
    	
//		System.err.println(studentrepository.findByregno(student.getRegno()));
		
	}

	public List<Student> getAllStudents() {
		return studentrepository.findAll();
	}

	public void deleteStudent(int regno) {
		Student student=studentrepository.findByregno(regno);
		studentrepository.delete(student);
	}

	public Student greet(User user) {
		Student student=studentrepository.findBynetid(user.getUsername()).orElseThrow(()->new UsernameNotFoundException("invalid netid"));
		return student;
	}

	public Student updateStudent(Long id,Student student) throws Exception {
		Student existing=studentrepository.findById(id).orElseThrow(()->new UsernameNotFoundException("Student not found"));
		System.out.println(existing);
		Student byRegno = studentrepository.findByregno(student.getRegno());
		if( byRegno!=null && byRegno.getId()!=existing.getId()) {
			throw new Exception("Student already registered with this registration number");
		}
		existing.setFirstname(student.getFirstname());
		existing.setLastname(student.getLastname());
		existing.setRegno(student.getRegno());
		existing.setGmail(student.getGmail());
		existing.setDob(student.getDob());
		existing.setDepartment(student.getDepartment());
		existing.setSpecialization(student.getSpecialization());
		existing.setAddress(student.getAddress());
		return studentrepository.save(existing);
	}

	public Student searchStudent(int regno) throws Exception {
			Student student= studentrepository.findByregno(regno);
			if(student!=null) return student;
			throw new Exception("Student not found");
	}
	
	public Student getByid(Long id) {
		return studentrepository.findById(id).orElseThrow(()->new UsernameNotFoundException("invalid id"));
	}
}
