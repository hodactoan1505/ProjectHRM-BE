package com.brycen.hrm.serivce.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.model.DepartmentEntity;
import com.brycen.hrm.repository.DepartmentRepository;
import com.brycen.hrm.request.DepartmentRequest;
import com.brycen.hrm.request.EmployeeRequest;
import com.brycen.hrm.response.Response;
import com.brycen.hrm.response.object.DepartmentResponse;
import com.brycen.hrm.service.DepartmentService;
import com.brycen.hrm.status.BaseConvert;
import com.brycen.hrm.status.Code;
import com.brycen.hrm.status.Message;

@Service
public class DepartmentImpl implements DepartmentService {

	@Autowired
	private BaseConvert baseConvert;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private DepartmentRepository departmentRepo;

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

    @Override
    public Response addDepartment(DepartmentRequest departmentRequest) {
        Response response = new Response();
        try {
            if(departmentRepo.findByName(departmentRequest.getName()) != null) {
                response.setCode(Code.department_already_exist);
                response.setMessage(Message.department_already_exist);
                return response;
            }
            
            DepartmentEntity entity = baseConvert.departmentRequestToEntity(departmentRequest);
            departmentRepo.save(entity);
            
        } catch (Exception e) {
            response.setCode(Code.unknown);
            response.setMessage(Message.unknown);
        }
        return response;
    }

    @Override
    public Response updateDepartment(DepartmentRequest departmentRequest) {
        Response response = new Response();
        try {
            Optional<DepartmentEntity> dOptional = departmentRepo.findById(departmentRequest.getId());
            
            if(dOptional.orElse(null) == null) {
                response.setCode(Code.department_not_found);
                response.setMessage(Message.department_not_found);
                return response;
            }           
            
            DepartmentEntity entity = dOptional.get();
            entity.setDescription(departmentRequest.getDescription());
            entity.setName(departmentRequest.getName());
            entity.setStartDate(departmentRequest.getStartdate());
            
            departmentRepo.save(entity);
            
        } catch (Exception e) {
            response.setCode(Code.unknown);
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
