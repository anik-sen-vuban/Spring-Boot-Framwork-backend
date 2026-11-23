package com.anik.JPARelationshipsDemoApplication.repository;

import org.springframework.stereotype.Repository;

import com.anik.JPARelationshipsDemoApplication.model.Department;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository 
public class DepartmentRepository {
    @PersistenceContext 
    private EntityManager entityManager;

    public void save (Department department){
        entityManager.persist(department);
    }

    public Department getDepartmentById(Long id){
        return entityManager.find(Department.class, id);
    }
}
