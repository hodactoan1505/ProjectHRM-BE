package com.brycen.hrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brycen.hrm.model.EmpSkillId;
import com.brycen.hrm.model.EmployeeSkillEntity;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkillEntity, EmpSkillId>{

}
