package com.xiaoming.spring.clinic.controller;

import com.xiaoming.spring.clinic.service.RegistrationService;
import com.xiaoming.spring.entity.dto.RegistrationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/async")
    public CompletableFuture<ResponseEntity<?>> createRegistrationAsync(
            @RequestBody RegistrationRequest request) {
        return registrationService.asyncCreateRegistration(request)
                .thenApply(res -> ResponseEntity.status(HttpStatus.CREATED).body(res));
    }
}