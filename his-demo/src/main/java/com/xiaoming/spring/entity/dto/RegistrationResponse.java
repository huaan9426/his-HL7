package com.xiaoming.spring.entity.dto;

import java.time.LocalDateTime;

public class RegistrationResponse {
    private Long registrationId;
    private String status;
    private LocalDateTime timeSlot;

    public RegistrationResponse(Long id, String status, LocalDateTime time) {
        this.registrationId = id;
        this.status = status;
        this.timeSlot = time;
    }

    // Getter方法必须存在
    public Long getRegistrationId() { return registrationId; }
    public String getStatus() { return status; }
    public LocalDateTime getTimeSlot() { return timeSlot; }
}
