package com.project.studentsManagementSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.studentsManagementSystem.Model.Student;
import com.project.studentsManagementSystem.Repository.StudentRepository;

@Service
public class StudentDetailsService implements UserDetailsService {
   @Autowired
   StudentRepository studentrepository;
	
	@Override
	public UserDetails loadUserByUsername(String netid) throws UsernameNotFoundException {
		Student obj=studentrepository.findBynetid(netid).orElseThrow(()->new UsernameNotFoundException("invalid netid"));
		return new User(obj.getNetid(),obj.getPassword(),List.of(new SimpleGrantedAuthority("Student")));
	}

}
