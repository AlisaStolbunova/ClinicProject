package com.clinic.dto;

import com.clinic.domain.Ticket;

import java.util.List;

public class DoctorDto {

    private Long id;
    private String name;
    private String post;
    private Long departmentId;
    private String departmentName;
    private String office;
    private String phoneNumber;
    private List<Ticket> tickets;

    public DoctorDto(Long id, String name, String post, Long departmentId, String office, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.post = post;
        this.departmentId = departmentId;
        this.office = office;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPost() {
        return post;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getOffice() {
        return office;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
