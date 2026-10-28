package com.student.StudentManagement.Repositories;

import com.student.StudentManagement.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository no need to use cause Spring Data JPA automatically detects interfaces that extend JpaRepository
public interface StudentRepository extends JpaRepository<Student, Long> {
//    List<Student> findByIsDeletedFalse();
//
//    Optional<Student> findByIdAndIsDeletedFalse(Long id);
}
