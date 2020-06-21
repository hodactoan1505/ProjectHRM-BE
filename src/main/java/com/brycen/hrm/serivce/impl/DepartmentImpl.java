package com.brycen.hrm.serivce.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.model.DepartmentEntity;
import com.brycen.hrm.request.DepartmentRequest;
import com.brycen.hrm.request.EmployeeRequest;
import com.brycen.hrm.response.Response;
import com.brycen.hrm.response.object.DepartmentResponse;
import com.brycen.hrm.service.DepartmentService;
import com.brycen.hrm.status.BaseConvert;
import com.brycen.hrm.status.Code;

@Service
public class DepartmentImpl implements DepartmentService {

	@Autowired
	private BaseConvert baseConvert;

	@Autowired
	private EntityManager entityManager;

	/**
	 * Hàm lấy danh sách phòng ban theo role Nếu trong employeeRequest có object
	 * department => chỉ lấy department Nếu trong employeeRequest null hết thì lấy
	 * toàn bộ
	 */
	@Override
	public Response getDepartment(EmployeeRequest employeeRequest) {
		Response response = new Response();
		try {

			String select = "SELECT DISTINCT  * ";
			String from = "FROM department ";
			String where = "WHERE department.isdelete = 0";

			List<DepartmentRequest> reqDepartment = employeeRequest.getDepartment();
			if (reqDepartment != null) {
				where += " and department.id = " + reqDepartment.get(0).getId();
			}

			String nativeQuery = select + from + where;
			@SuppressWarnings("unchecked")
			Query<DepartmentEntity> query = (Query<DepartmentEntity>) entityManager.createNativeQuery(nativeQuery,
					DepartmentEntity.class);

			List<DepartmentEntity> list = query.getResultList();
			List<DepartmentResponse> result = new ArrayList<DepartmentResponse>();
			for (DepartmentEntity entity : list) {
				DepartmentResponse resDepartment = baseConvert.departmentToResponse(entity);
				result.add(resDepartment);
			}

			response.setData(result);

		} catch (Exception e) {
			response.setCode(Code.unknown);
			response.setMessage(e.getMessage());
		}
		return response;
	}

}
