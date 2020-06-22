package com.brycen.hrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.request.EmployeeRequest;
import com.brycen.hrm.request.UserRequest;
import com.brycen.hrm.response.Response;
import com.brycen.hrm.service.DepartmentService;
import com.brycen.hrm.service.EmployeeService;
import com.brycen.hrm.service.ProjectService;
import com.brycen.hrm.service.SkillService;

/**
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class HomeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private SkillService skillService;
    
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
    
    /**
     * [Description]: Xử lý lấy dữ liệu danh sách nhân viên, tìm kiếm
     * [ Remarks ]:<br/>
     *
     * @param employeeRequest
     * @return Response
     */
    @PostMapping("/employee")
    public Response getEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.getEmployee(employeeRequest);
    }
    
    /**
     * [Description]:Xử lý thêm mới nhân viên=> chỉ admin
     * [ Remarks ]:<br/>
     *
     * @param employeeRequest
     * @return Response
     */
    @PostMapping("/employee/add")
    public Response addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.addEmployee(employeeRequest);
    }
    
    
    /**
     * [Description]:Xử lý lấy dữ liệu phòng ban
     * [ Remarks ]:<br/>
     *
     * @param employeeRequest
     * @return Response
     */
    @PostMapping("/department")
    public Response getDepartment(@RequestBody EmployeeRequest employeeRequest) {
        return departmentService.getDepartment(employeeRequest);
    }
    
    /**
     * [Description]:Xử lý lấy dữ liệu dự án
     * [ Remarks ]:<br/>
     *
     * @param employeeRequest
     * @return Response
     */
    @PostMapping("/project")
    public Response getProject(@RequestBody EmployeeRequest employeeRequest) {
        return projectService.getAllProject(employeeRequest);
    }
    
    /**
     * [Description]:Xử lý lấy danh sách kĩ năng
     * [ Remarks ]:<br/>
     *
     * @return Response
     */
    @GetMapping("/skill")
    public Response getSkill() {
    	return skillService.getAllSkill();
    }
    
    /**
     * [Description]: xử lý cập nhật thông tin nhân viên
     * [ Remarks ]:<br/>
     *
     * @param employeeRequest
     * @return Response
     */
    @PutMapping("/employee/update")
    public Response updateEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.updateEmployee(employeeRequest);
    }
    
    @DeleteMapping("/employee/delete")
    public Response deleteEmployee(@RequestBody List<Integer> list) {
        return employeeService.deleteEmployee(list);
    }
}
