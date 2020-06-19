package com.brycen.hrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brycen.hrm.model.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>{
    EmployeeEntity findByUsername(String username);
}
