package com.clinic.controllers;

import com.clinic.domain.Department;
import com.clinic.domain.Doctor;
import com.clinic.domain.User;
import com.clinic.dto.DoctorDto;
import com.clinic.services.ClinicUserDetailService;
import com.clinic.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ClinicUserDetailService userDetailService;

    @GetMapping("/departments")
    public String getDepartments(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return "departments";
    }

    @GetMapping("/departments/doctors")
    public String getDoctors(@RequestParam Long id, Model model) {
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("doctors", mapToDto(departmentService
                .findById(id)
                .map(Department::getDoctors).get()));
        return "departments";
    }


    @PostMapping("/doctor/ticket")
    public String takeTicket(Model model, Authentication authentication, @RequestParam Long id){
        User user = ((ClinicUserDetailService.MyUserDetails) authentication.getPrincipal()).getUser();
        userDetailService.addTicket(user, id);
        return getDepartments(model);
    }

    private List<DoctorDto> mapToDto(List<Doctor> doctors){
        List<DoctorDto> dtos = new ArrayList<>(doctors.size());
        for (Doctor doctor : doctors) {
            DoctorDto dto = new DoctorDto(
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getPost(),
                    doctor.getDepartment().getId(),
                    doctor.getOffice(),
                    doctor.getPhoneNumber());
            dto.setDepartmentName(doctor.getDepartment().getName());
            dto.setTickets(doctor.getTickets().stream().filter(ticket -> ticket.getUser() == null).collect(Collectors.toList()));
            dtos.add(dto);
        }
        return dtos;
    }
}
