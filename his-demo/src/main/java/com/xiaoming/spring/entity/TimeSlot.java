package com.xiaoming.spring.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // Lombok annotations
    @Getter
    @Setter
    @Builder
    public static class Projection {
        private final Long doctorId;
        private final LocalDateTime startTime;
        private final LocalDateTime endTime;

        public Projection(
                @JsonProperty("doctorId") Long doctorId,
                @JsonProperty("startTime") LocalDateTime startTime,
                @JsonProperty("endTime") LocalDateTime endTime
        ) {
            this.doctorId = doctorId;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        // 必须添加无参构造函数
        public Projection() {
            this(null, null, null);
        }
    }


    public TimeSlot() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
