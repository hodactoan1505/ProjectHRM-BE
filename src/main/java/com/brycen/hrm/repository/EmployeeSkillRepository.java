package com.brycen.hrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.brycen.hrm.model.EmpSkillId;
import com.brycen.hrm.model.EmployeeSkillEntity;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkillEntity, EmpSkillId>{
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM employeeskill WHERE employeeskill.employeeid = ?1", nativeQuery = true)
    void deleteSkillofEmp(int employeeId);
}
