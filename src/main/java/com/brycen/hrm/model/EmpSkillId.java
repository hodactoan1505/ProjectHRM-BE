package com.brycen.hrm.model;

import java.io.Serializable;

/**
 * [Description]:Class two primary key of Employee and Skill
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class EmpSkillId implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int employee;
    private int skill;

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + employee;
        result = prime * result + skill;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmpSkillId other = (EmpSkillId) obj;
        if (employee != other.employee)
            return false;
        if (skill != other.skill)
            return false;
        return true;
    }

}
