package com.xiaoming.spring.Repository;

import com.xiaoming.spring.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
