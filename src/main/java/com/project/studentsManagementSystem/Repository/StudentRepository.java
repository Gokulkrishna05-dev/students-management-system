package com.project.studentsManagementSystem.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.studentsManagementSystem.Model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>  {
       Optional<Student> findBynetid(String netid);
//       Optional<Student> findByregno(int regno);
         Student findByregno(int regno);
}
