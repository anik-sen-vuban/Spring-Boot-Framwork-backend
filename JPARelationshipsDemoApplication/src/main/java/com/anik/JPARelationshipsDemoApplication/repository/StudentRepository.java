package com.anik.JPARelationshipsDemoApplication.repository;

import org.springframework.stereotype.Repository;

import com.anik.JPARelationshipsDemoApplication.model.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository 
public class StudentRepository {
    @PersistenceContext 
    private EntityManager entityManager;

    public void save (Student student){
        entityManager.persist(student);
    }
}
