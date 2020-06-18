package com.brycen.hrm.serivce.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.model.EmployeeEntity;
import com.brycen.hrm.repository.EmployeeRepository;
import com.brycen.hrm.response.Response;
import com.brycen.hrm.response.object.EmployeeResponse;
import com.brycen.hrm.service.EmployeeService;
import com.brycen.hrm.status.BaseConvert;
import com.brycen.hrm.status.Code;

@Service
public class EmployeeImpl implements EmployeeService{
    
    @Autowired
    private EmployeeRepository employeeRepo;
    
    @Autowired
    private BaseConvert baseConvert;
    
    @Override
    public Response getEmployee() {
        Response response = new  Response();
        try {
            
            List<EmployeeEntity> list = employeeRepo.getEmployee("and employee.id = 1");
            
            List<EmployeeResponse> listEmpReponse = new ArrayList<EmployeeResponse>();
            
            for (EmployeeEntity employeeEntity : list) {
                EmployeeResponse empResponse = baseConvert.EmployeeToResponse(employeeEntity);
                listEmpReponse.add(empResponse);
            }
            
            response.setData(listEmpReponse);
            
        } catch (Exception e) {
            response.setCode(Code.unknown);
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
