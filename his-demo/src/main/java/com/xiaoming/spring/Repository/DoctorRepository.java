package com.xiaoming.spring.Repository;

import com.xiaoming.spring.entity.Doctor;
import com.xiaoming.spring.entity.TimeSlot;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // 添加带锁查询方法
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT d FROM Doctor d " +
            "WHERE d.department.id = :departmentId " +
            "AND :timeSlot BETWEEN d.availableStart AND d.availableEnd")
    Doctor findByDepartmentAndTimeSlotWithLock(
            @Param("departmentId") Long departmentId,
            @Param("timeSlot") LocalDateTime timeSlot);

    // 修正投影查询
    @Query("SELECT NEW com.xiaoming.spring.entity.TimeSlot$Projection(" +
            "d.id, d.availableStart, d.availableEnd) " +
            "FROM Doctor d WHERE d.department.id = :departmentId " +
            "AND FUNCTION('DATE', d.availableStart) = :date")
    List<TimeSlot.Projection> findAvailableSlots(
            @Param("departmentId") Long departmentId,
            @Param("date") LocalDate date);
}