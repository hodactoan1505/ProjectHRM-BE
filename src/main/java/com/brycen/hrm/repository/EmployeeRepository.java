package com.brycen.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.brycen.hrm.model.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>{

    @Transactional
    @Query(value = "select distinct * "+
            "    from employee,project,department,skill,employeeskill\r\n" + 
            "    where employee.departmentid = department.id\r\n" + 
            "    and department.id = project.departmentid\r\n" + 
            "    and employee.projectid = project.id\r\n" + 
            "    and employee.id = employeeskill.employeeid\r\n" + 
            "    and employeeskill.skillid = skill.id\r\n" + 
            "    and project.name like '%ems%'\r\n" + 
            "    and department.name like '%ems%'\r\n" + 
            "    and skill.name like '%java%'\r\n ?1", nativeQuery = true)
    List<EmployeeEntity> getEmployee(String query);
}
