package com.xiaoming.spring.Repository;
import com.xiaoming.spring.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
