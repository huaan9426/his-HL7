package com.xiaoming.spring.WebController;
import com.xiaoming.spring.Service.DepartmentService;
import com.xiaoming.spring.Service.DoctorService;
import com.xiaoming.spring.entity.Doctor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctors")
public class DoctorWebController {
    private final DoctorService doctorService;

    public DoctorWebController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // 显示医生列表页面
    @GetMapping
    public String listDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctor/list";
    }

    // 显示创建医生页面
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("departments", DepartmentService.getAllDepartments());
        return "doctor/create";
    }

    // 处理创建医生请求
    @PostMapping
    public String createDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "redirect:/doctors";
    }

    // 显示更新医生页面
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Doctor> doctorOptional = doctorService.getDoctorById(id);
        if (doctorOptional.isPresent()) {
            model.addAttribute("doctor", doctorOptional.get());
            model.addAttribute("departments", DepartmentService.getAllDepartments());
            return "doctor/edit";
        } else {
            return "error/404";
        }
    }

    // 处理更新医生请求
    @PostMapping("/{id}")
    public String updateDoctor(@PathVariable Long id, @ModelAttribute("doctor") Doctor doctor) {
        Optional<Doctor> doctorOptional = doctorService.getDoctorById(id);
        if (doctorOptional.isPresent()) {
            Doctor existingDoctor = doctorOptional.get();
            existingDoctor.setName(doctor.getName());
            existingDoctor.setContact(doctor.getContact());
            existingDoctor.setDepartment(doctor.getDepartment());
            doctorService.saveDoctor(existingDoctor);
        }
        return "redirect:/doctors";
    }

    // 处理删除医生请求
    @GetMapping("/{id}/delete")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }
}
