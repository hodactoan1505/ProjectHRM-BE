package com.brycen.hrm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employeeskill")
@IdClass(EmpSkillId.class)
public class EmployeeSkillEntity {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "employeeid", nullable = false)
    private EmployeeEntity employee;
    
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "skillid", nullable = false)
    private SkillEntity skill;

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public SkillEntity getSkill() {
        return skill;
    }

    public void setSkill(SkillEntity skill) {
        this.skill = skill;
    }
    
    public EmployeeSkillEntity() {
        
    }
}
