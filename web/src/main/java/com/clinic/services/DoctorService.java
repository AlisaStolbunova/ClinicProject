package com.clinic.services;

import com.clinic.domain.Doctor;
import com.clinic.domain.Ticket;
import com.clinic.dto.DoctorDto;
import com.clinic.repositories.DepartmentRepository;
import com.clinic.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService extends ClinicService<Doctor> {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public CrudRepository<Doctor, Long> repository() {
        return doctorRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void create(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorDto.getName());
        doctor.setDepartment(departmentRepository.findById(doctorDto.getDepartmentId()).get());
        doctor.setOffice(doctorDto.getOffice());
        doctor.setPhoneNumber(doctorDto.getPhoneNumber());
        doctor.setPost(doctorDto.getPost());
        doctor.setTickets(generateTickets(doctor));
        doctorRepository.save(doctor);
    }

    public static List<Ticket> generateTickets(Doctor doctor){
        LocalDateTime current = LocalDateTime.now();
        List<Ticket> tickets = new ArrayList<>(40);
        while (tickets.size() < 40){
            Ticket ticket = new Ticket();
            ticket.setDoctor(doctor);
            LocalDateTime nextWorkingHour = addNextWorkingHour(current.truncatedTo(ChronoUnit.HOURS));
            ticket.setTime(nextWorkingHour);
            tickets.add(ticket);
            current = nextWorkingHour;
        }
        return tickets;
    }

    public static LocalDateTime addNextWorkingHour(LocalDateTime current) {
        if (current.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return LocalDateTime.of(current.toLocalDate().plusDays(2), LocalTime.of(8, 0));
        } else if (current.getDayOfWeek() == DayOfWeek.SUNDAY){
            return LocalDateTime.of(current.toLocalDate().plusDays(1), LocalTime.of(8, 0));
        } else if(current.getHour() < 17){
            return current.plusHours(1);
        } else {
            return addNextWorkingHour(LocalDateTime.of(current.toLocalDate().plusDays(1), LocalTime.of(7, 0)));
        }

    }



}
