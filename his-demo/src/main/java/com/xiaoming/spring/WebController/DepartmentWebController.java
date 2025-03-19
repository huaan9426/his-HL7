package com.xiaoming.spring.WebController;
import com.xiaoming.spring.Service.DepartmentService;
import com.xiaoming.spring.entity.Department;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/departments")
public class DepartmentWebController {
    private final DepartmentService departmentService;

    public DepartmentWebController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // 显示科室列表页面
    @GetMapping
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "department/list";
    }

    // 显示创建科室页面
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/create";
    }

    // 处理创建科室请求
    @PostMapping
    public String createDepartment(@ModelAttribute("department") Department department) {
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }

    // 显示更新科室页面
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Department> departmentOptional = departmentService.getDepartmentById(id);
        if (departmentOptional.isPresent()) {
            model.addAttribute("department", departmentOptional.get());
            return "department/edit";
        } else {
            return "error/404";
        }
    }

    // 处理更新科室请求
    @PostMapping("/{id}")
    public String updateDepartment(@PathVariable Long id, @ModelAttribute("department") Department department) {
        Optional<Department> departmentOptional = departmentService.getDepartmentById(id);
        if (departmentOptional.isPresent()) {
            Department existingDepartment = departmentOptional.get();
            existingDepartment.setName(department.getName());
            existingDepartment.setDescription(department.getDescription());
            departmentService.saveDepartment(existingDepartment);
        }
        return "redirect:/departments";
    }

    // 处理删除科室请求
    @GetMapping("/{id}/delete")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}
