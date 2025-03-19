package com.xiaoming.spring.WebController;

import com.xiaoming.spring.Service.PatientService;
import com.xiaoming.spring.entity.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/patients")
public class PatientWebController {
    private final PatientService patientService;

    public PatientWebController(PatientService patientService) {
        this.patientService = patientService;
    }

    // 显示患者列表页面
    @GetMapping
    public String listPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patient/list";
    }

    // 显示创建患者页面
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/create";
    }

    // 处理创建患者请求
    @PostMapping
    public String createPatient(@ModelAttribute("patient") Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/patients";
    }

    // 显示更新患者页面
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Patient> patientOptional = patientService.getPatientById(id);
        if (patientOptional.isPresent()) {
            model.addAttribute("patient", patientOptional.get());
            return "patient/edit";
        } else {
            return "error/404";
        }
    }

    // 处理更新患者请求
    @PostMapping("/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute("patient") Patient patient) {
        Optional<Patient> patientOptional = patientService.getPatientById(id);
        if (patientOptional.isPresent()) {
            Patient existingPatient = patientOptional.get();
            existingPatient.setName(patient.getName());
            existingPatient.setAge(patient.getAge());
            existingPatient.setGender(patient.getGender());
            existingPatient.setContact(patient.getContact());
            patientService.savePatient(existingPatient);
        }
        return "redirect:/patients";
    }

    // 处理删除患者请求
    @GetMapping("/{id}/delete")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
