# his-HL7
hisdemo增强版
![2dab5ddb972314c0a9865d704a06246](https://github.com/user-attachments/assets/ebb3c5e4-e6dd-489c-9d55-8ae31cd96ad6)
![6e69afb4c9cf82e19c1146df87580e6](https://github.com/user-attachments/assets/c88cc68a-48a4-416b-a86b-def088f82f25)
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─xiaoming
│  │  │          └─spring
│  │  │              │  HisDemoApplication.java
│  │  │              │  
│  │  │              ├─cache
│  │  │              │      RedisConfig.java
│  │  │              │      RegistrationCache.java
│  │  │              │      
│  │  │              ├─clinic
│  │  │              │  ├─controller
│  │  │              │  │      RegistrationController.java
│  │  │              │  │      
│  │  │              │  ├─hl7
│  │  │              │  │      HL7Parser.java
│  │  │              │  │      HL7Validator.java
│  │  │              │  │      
│  │  │              │  ├─model
│  │  │              │  └─service
│  │  │              │          RegistrationService.java
│  │  │              │          
│  │  │              ├─config
│  │  │              │      AuditLogAspect.java
│  │  │              │      SecurityConfig.java
│  │  │              │      ThreadPoolConfig.java
│  │  │              │      TransactionConfig.java
│  │  │              │      
│  │  │              ├─Controller
│  │  │              │      DepartmentController.java
│  │  │              │      DoctorController.java
│  │  │              │      PatientController.java
│  │  │              │      
│  │  │              ├─entity
│  │  │              │  │  Department.java
│  │  │              │  │  Doctor.java
│  │  │              │  │  MedicalEvent.java
│  │  │              │  │  Patient.java
│  │  │              │  │  Registration.java
│  │  │              │  │  TimeSlot.java
│  │  │              │  │  
│  │  │              │  └─dto
│  │  │              │          RegistrationRequest.java
│  │  │              │          RegistrationResponse.java
│  │  │              │          
│  │  │              ├─Repository
│  │  │              │      DepartmentRepository.java
│  │  │              │      DoctorRepository.java
│  │  │              │      PatientRepository.java
│  │  │              │      RegistrationRepository.java
│  │  │              │      
│  │  │              ├─security
│  │  │              │      HL7EncryptionFilter.java
│  │  │              │      MedicalDataEncryptor.java
│  │  │              │      
│  │  │              ├─Service
│  │  │              │      DepartmentService.java
│  │  │              │      DoctorService.java
│  │  │              │      PatientService.java
│  │  │              │      
│  │  │              ├─threadpool
│  │  │              │      RegistrationThreadPool.java
│  │  │              │      
│  │  │              ├─util
│  │  │              │      SpringContextUtil.java
│  │  │              │      
│  │  │              └─WebController
│  │  │                      DepartmentWebController.java
│  │  │                      DoctorWebController.java
│  │  │                      PatientWebController.java
│  │  │                      
│  │  └─resources
│  │      │  application.properties
│  │      │  
│  │      ├─static
│  │      └─templates
│  │          ├─department
│  │          │      create.html
│  │          │      edit.html
│  │          │      list.html
│  │          │      
│  │          ├─doctor
│  │          │      create.html
│  │          │      edit.html
│  │          │      list.html
│  │          │      
│  │          └─patient
│  │                  create.html
│  │                  edit.html
│  │                  list.html
│  │                  
│  └─test
│      └─java
│          └─com
│              └─xiaoming
│                  └─spring
│                          HisDemoApplicationTests.java
