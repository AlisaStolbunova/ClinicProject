package com.clinic.services;

import com.clinic.domain.Department;
import com.clinic.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends ClinicService<Department> {

    @Autowired
    private DepartmentRepository repository;

    @Override
    public CrudRepository<Department, Long> repository() {
        return repository;
    }
}
