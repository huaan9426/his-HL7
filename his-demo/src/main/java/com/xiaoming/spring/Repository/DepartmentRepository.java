package com.xiaoming.spring.Repository;

import com.xiaoming.spring.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
