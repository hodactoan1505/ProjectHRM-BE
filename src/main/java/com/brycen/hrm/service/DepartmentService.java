package com.brycen.hrm.service;

import com.brycen.hrm.request.DepartmentRequest;
import com.brycen.hrm.request.EmployeeRequest;
import com.brycen.hrm.response.Response;

public interface DepartmentService {
    Response getDepartment(EmployeeRequest employeeRequest);
    Response addDepartment(DepartmentRequest departmentRequest);
    Response updateDepartment(DepartmentRequest departmentRequest);
}
