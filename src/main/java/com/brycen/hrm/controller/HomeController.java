package com.brycen.hrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.request.UserRequest;
import com.brycen.hrm.response.Response;
import com.brycen.hrm.service.EmployeeService;
import com.brycen.hrm.service.UserService;

@CrossOrigin
@RestController
public class HomeController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EmployeeService employeeService;
    
    /**
     * [Description]: Xử lý đăng nhập
     * [ Remarks ]:<br/>
     *
     * @param userRequest
     * @return Response
     */
    @PostMapping("/login")
    public Response login(@RequestBody UserRequest userRequest) {
        return userService.login(userRequest);
    }
    
    @GetMapping("/employee")
    public Response getEmployee() {
        return employeeService.getEmployee();
    }
}
