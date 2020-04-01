package com.clinic.services;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public abstract class ClinicService<T> {

    abstract CrudRepository<T, Long> repository();

    public Iterable<T> findAll(){
        return repository().findAll();
    }

    public Optional<T> findById(Long id){
        return repository().findById(id);
    }
}
