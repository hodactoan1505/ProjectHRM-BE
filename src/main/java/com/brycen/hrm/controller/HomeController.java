package com.brycen.hrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.request.EmployeeRequest;
import com.brycen.hrm.request.UserRequest;
import com.brycen.hrm.response.Response;
import com.brycen.hrm.service.DepartmentService;
import com.brycen.hrm.service.EmployeeService;
import com.brycen.hrm.service.ProjectService;

@CrossOrigin
@RestController
public class HomeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private ProjectService projectService;
    
    /**
     * [Description]: Xử lý đăng nhập
     * [ Remarks ]:<br/>
     *
     * @param userRequest
     * @return Response
     */
    @PostMapping("/login")
    public Response login(@RequestBody UserRequest userRequest) {
        return employeeService.login(userRequest);
    }
    
    @PostMapping("/employee")
    public Response getEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.getEmployee(employeeRequest);
    }
    
    @GetMapping("/employee/view")
    public String viewEmployee() {
        return "abc";
    }
    
    @GetMapping("/department")
    public Response getDepartment() {
        return departmentService.getAllDepartment();
    }
    
    @GetMapping("/project")
    public Response getProject() {
        return projectService.getAllProject();
    }
}
