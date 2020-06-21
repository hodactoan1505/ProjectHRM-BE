package com.brycen.hrm.service;

import com.brycen.hrm.request.EmployeeRequest;
import com.brycen.hrm.response.Response;

public interface ProjectService {
    Response getAllProject(EmployeeRequest employeeRequest);
}
