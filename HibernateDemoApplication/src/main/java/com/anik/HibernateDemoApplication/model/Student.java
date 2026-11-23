package com.anik.HibernateDemoApplication.model;

import java.util.Set;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table (name = "students")
public class Student {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
        name = "student_name",
        nullable = false
    )
    private String name;

    @Column (
        name = "student_email",
        unique = true,
        insertable = true,
        updatable = false
    )
    private String email;

    private int age;

    @Convert (converter = BooleanToStringConverter.class)
    private boolean active;

    @Enumerated (EnumType.STRING)
    private StudentStatus status = StudentStatus.ACTIVE;

    @ElementCollection 
    @CollectionTable (
        name = "student_skills",
        joinColumns = @JoinColumn (name = "student_id")
    )
    private Set<String> skills;

    @Embedded
    @AttributeOverrides ({
        @AttributeOverride (
            name = "houseNo",
            column = @Column (name = "current_house_no")
        ),
        @AttributeOverride(
            name = "street",
            column = @Column (name = "current_street")
        ),
        @AttributeOverride (
            name = "city",
            column = @Column (name = "current_city")
        ),
        @AttributeOverride (
            name = "state",
            column = @Column (name = "current_state")
        ),
        @AttributeOverride (
            name = "pincode",
            column = @Column (name = "current_pincode")
        )
    })
    private Address currentAddress;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
    }

    

}
