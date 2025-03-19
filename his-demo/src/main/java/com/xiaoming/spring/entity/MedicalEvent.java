package com.xiaoming.spring.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "medical_events")
public class MedicalEvent {
    @Id
    @Column(name = "patient_id", length = 50)
    private String patientId;

    @Column(name = "event_type", length = 20)
    private String eventType;

    @Column(name = "event_time")
    private LocalDateTime eventTime;

    // 手动实现 Builder 模式
    public static Builder builder() {
        return new Builder();
    }

    // 无参构造器（必须）
    public MedicalEvent() {}

    // 全参构造器（可选）
    private MedicalEvent(String patientId, String eventType, LocalDateTime eventTime) {
        this.patientId = patientId;
        this.eventType = eventType;
        this.eventTime = eventTime;
    }

    // Getter 和 Setter 方法（必须）
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public LocalDateTime getEventTime() { return eventTime; }
    public void setEventTime(LocalDateTime eventTime) { this.eventTime = eventTime; }

    // 内部 Builder 类
    public static class Builder {
        private String patientId;
        private String eventType;
        private LocalDateTime eventTime;

        public Builder patientId(String patientId) {
            this.patientId = patientId;
            return this;
        }

        public Builder eventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder eventTime(LocalDateTime eventTime) {
            this.eventTime = eventTime;
            return this;
        }

        public MedicalEvent build() {
            return new MedicalEvent(patientId, eventType, eventTime);
        }
    }
}
