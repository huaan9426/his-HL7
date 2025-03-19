package com.xiaoming.spring.entity;
import com.xiaoming.spring.security.MedicalDataEncryptor;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class Patient {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    private String name;
    private Integer age;
    private String gender;
    private String contact;

    @Convert(converter = MedicalDataEncryptor.class)
    private String encryptedData;

    @Convert(converter = MedicalDataEncryptor.class)
    private String sensitiveData;

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getSensitiveData() {
        return sensitiveData;
    }

    public void setSensitiveData(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getContact() {
        return contact;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
