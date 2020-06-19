package com.brycen.hrm.serivce.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.model.ProjectEntity;
import com.brycen.hrm.repository.ProjectRepository;
import com.brycen.hrm.response.Response;
import com.brycen.hrm.response.object.ProjectResponse;
import com.brycen.hrm.service.ProjectService;
import com.brycen.hrm.status.BaseConvert;
import com.brycen.hrm.status.Code;

@Service
public class ProjectImpl implements ProjectService{
    
    @Autowired
    private ProjectRepository projectRepos;
    
    @Autowired
    private BaseConvert baseConvert;
    
    @Override
    public Response getAllProject() {
        Response response = new Response();
        try {
            List<ProjectEntity> list = projectRepos.findAll();
            List<ProjectResponse> result = new ArrayList<ProjectResponse>();
            
            for (ProjectEntity entity : list) {
                ProjectResponse resProject = baseConvert.projectToResponse(entity);
                result.add(resProject);
            }
            
            response.setData(result);
            
        } catch (Exception e) {
            response.setCode(Code.unknown);
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
