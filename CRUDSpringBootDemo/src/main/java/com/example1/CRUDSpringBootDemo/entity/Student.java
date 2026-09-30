package com.example1.CRUDSpringBootDemo.entity;

import jakarta.persistence.*;

@Entity //it referes Student class will be a table of a database
@Table(name="students") //tells JPA the name of the database table, without it JPA usually uses the class name as the table name
public class Student {

    @Id //to make a unique primary key for a object of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) //or AUTO. UUID(like random 550e8400-e29b...)
    private Long id;

    private String name;
    private int age;
    private String email;
    private int rollNo;
    private String subject;
    //for soft deleting
    private boolean /*int*/ isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }
}
