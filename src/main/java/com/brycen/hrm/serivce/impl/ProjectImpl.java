package com.brycen.hrm.serivce.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.model.ProjectEntity;
import com.brycen.hrm.request.DepartmentRequest;
import com.brycen.hrm.request.EmployeeRequest;
import com.brycen.hrm.request.ProjectRequest;
import com.brycen.hrm.response.Response;
import com.brycen.hrm.response.object.ProjectResponse;
import com.brycen.hrm.service.ProjectService;
import com.brycen.hrm.status.BaseConvert;
import com.brycen.hrm.status.Code;

@Service
public class ProjectImpl implements ProjectService {

	@Autowired
	private BaseConvert baseConvert;

	@Autowired
	private EntityManager entityManager;

	@Override
	public Response getAllProject(EmployeeRequest employeeRequest) {
		Response response = new Response();
		try {

			String select = "SELECT DISTINCT  * ";
			String from = "FROM project ";
			String where = "WHERE project.isdelete = 0";

			List<DepartmentRequest> reqDepartment = employeeRequest.getDepartment();
			if (reqDepartment != null) {
				from += ", department ";
				where += " and project.departmentid = department.id and department.id = "
						+ reqDepartment.get(0).getId();
			}

			List<ProjectRequest> reqProject = employeeRequest.getProject();
			if (reqProject != null) {
				where += " and project.id = " + reqProject.get(0).getId();
			}

			String nativeQuery = select + from + where;
			@SuppressWarnings("unchecked")
			Query<ProjectEntity> query = (Query<ProjectEntity>) entityManager.createNativeQuery(nativeQuery,
					ProjectEntity.class);

			List<ProjectEntity> list = query.getResultList();
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
