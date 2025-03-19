package com.xiaoming.spring.clinic.service;
import com.xiaoming.spring.entity.Doctor;
import com.xiaoming.spring.entity.Registration;
import com.xiaoming.spring.Repository.DoctorRepository;
import com.xiaoming.spring.Repository.RegistrationRepository;
import com.xiaoming.spring.entity.dto.RegistrationRequest;
import com.xiaoming.spring.entity.dto.RegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import java.util.concurrent.CompletableFuture;

@Service
public class RegistrationService {
    private final DoctorRepository doctorRepository;
    private final RegistrationRepository registrationRepository;
    private final TransactionTemplate transactionTemplate;

    @Autowired
    public RegistrationService(DoctorRepository doctorRepository,
                               RegistrationRepository registrationRepository,
                               TransactionTemplate transactionTemplate) {
        this.doctorRepository = doctorRepository;
        this.registrationRepository = registrationRepository;
        this.transactionTemplate = transactionTemplate;
    }

    @Async("registrationThreadPool")
    public CompletableFuture<RegistrationResponse> asyncCreateRegistration(RegistrationRequest request) {
        return CompletableFuture.supplyAsync(() ->
                transactionTemplate.execute(status -> {
                    Doctor doctor = doctorRepository.findByDepartmentAndTimeSlotWithLock(
                            request.getDepartmentId(),
                            request.getTimeSlot()
                    );

                    Registration registration = new Registration();
                    registration.setDoctor(doctor);
                    registration.setTimeSlot(request.getTimeSlot());
                    registration.setStatus("REGISTERED");

                    Registration saved = registrationRepository.save(registration);

                    return new RegistrationResponse(
                            saved.getId(),
                            saved.getStatus(),
                            saved.getTimeSlot()
                    );
                })
        );
    }
}
