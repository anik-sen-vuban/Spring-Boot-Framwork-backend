package com.example.demoStudentManagementDto.Repositories;

import com.example.demoStudentManagementDto.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByIdAndDeletedIsFalse(Long id);

    List<Student> findByDeletedIsFalse();

    boolean existsByIdAndDeletedIsFalse(Long id);

    boolean existsByEmailAndDeletedIsFalse(String email);
}
