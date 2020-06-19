package com.brycen.hrm.serivce.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.model.DepartmentEntity;
import com.brycen.hrm.repository.DepartmentRepository;
import com.brycen.hrm.response.Response;
import com.brycen.hrm.response.object.DepartmentResponse;
import com.brycen.hrm.service.DepartmentService;
import com.brycen.hrm.status.BaseConvert;
import com.brycen.hrm.status.Code;

@Service
public class DepartmentImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private BaseConvert baseConvert;
    
    @Override
    public Response getAllDepartment() {
       Response response = new Response();
       try {
           List<DepartmentEntity> list = departmentRepository.findAll();
           List<DepartmentResponse> result = new ArrayList<DepartmentResponse>();
           for (DepartmentEntity entity : list) {
              DepartmentResponse resDepartment = baseConvert.departmentToResponse(entity);
              result.add(resDepartment);
           }
           
           response.setData(result);
           
       }catch (Exception e) {
           response.setCode(Code.unknown);
           response.setMessage(e.getMessage());
    }
        return response;
    }

}
