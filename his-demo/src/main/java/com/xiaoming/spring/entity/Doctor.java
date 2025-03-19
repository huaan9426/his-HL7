package com.xiaoming.spring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contact;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "available_time_slot")
    private LocalDateTime availableTimeSlot;

    @Column(name = "available_start")
    private LocalDateTime availableStart;

    @Column(name = "available_end")
    private LocalDateTime availableEnd;



    public LocalDateTime getAvailableStart() {
        return availableStart;
    }

    public void setAvailableStart(LocalDateTime availableStart) {
        this.availableStart = availableStart;
    }

    public LocalDateTime getAvailableEnd() {
        return availableEnd;
    }

    public void setAvailableEnd(LocalDateTime availableEnd) {
        this.availableEnd = availableEnd;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public Department getDepartment() {
        return department;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAvailableTimeSlot() {
        return availableTimeSlot;
    }

    public void setAvailableTimeSlot(LocalDateTime availableTimeSlot) {
        this.availableTimeSlot = availableTimeSlot;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
