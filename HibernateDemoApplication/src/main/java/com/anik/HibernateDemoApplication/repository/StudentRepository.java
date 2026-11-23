package com.anik.HibernateDemoApplication.repository;

import org.springframework.stereotype.Repository;

import com.anik.HibernateDemoApplication.model.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository 
public class StudentRepository {
    @PersistenceContext
    private EntityManager entiryManager;

    //create
    public void save(Student student){
        entiryManager.persist(student);
    }

    //read
    public Student findById(Long id){
        return entiryManager.find(Student.class, id);
    }

    //delete
    public void remove(Student student){
        entiryManager.remove(student);
    }
}
