package com.project.studentsManagementSystem.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.studentsManagementSystem.Model.Admin;

@Repository
public interface AdminRepository  extends JpaRepository<Admin,Long>{
	 Optional<Admin> findBynetid(String netid);
}
