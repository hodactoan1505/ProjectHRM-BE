package com.brycen.hrm.service;

import com.brycen.hrm.request.EmployeeRequest;
import com.brycen.hrm.request.UserRequest;
import com.brycen.hrm.response.Response;

public interface EmployeeService {
    Response login(UserRequest userRequest);
    Response getEmployee(EmployeeRequest employeeRequest);
}
