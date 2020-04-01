package com.clinic.controllers;

import com.clinic.dto.DoctorDto;
import com.clinic.services.DepartmentService;
import com.clinic.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/doctor/create")
    public String create(DoctorDto doctor, Model model){
        doctorService.create(doctor);
        return findAll(model);
    }

    @GetMapping("/doctor/all")
    public String findAll(Model model){
        model.addAttribute("doctors", doctorService.findAll());
        model.addAttribute("departments", departmentService.findAll());
        return "doctor";
    }
}
