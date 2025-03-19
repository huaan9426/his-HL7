package com.xiaoming.spring.cache;

import com.xiaoming.spring.Repository.DoctorRepository;
import com.xiaoming.spring.entity.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RegistrationCache {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private RedisTemplate<String, List<TimeSlot.Projection>> redisTemplate;

    @Cacheable(value = "registrations", key = "#departmentId+'-'+#date")
    public List<TimeSlot.Projection> getAvailableSlots(Long departmentId, LocalDate date) {
        String key = buildKey(departmentId, date);
        List<TimeSlot.Projection> slots = redisTemplate.opsForValue().get(key);

        if (slots == null) {
            slots = doctorRepository.findAvailableSlots(departmentId, date);
            redisTemplate.opsForValue().set(key, slots, 5, TimeUnit.MINUTES);
        }
        return slots;
    }

    private String buildKey(Long departmentId, LocalDate date) {
        return "registration:" + departmentId + ":" + date.toString();
    }
}

