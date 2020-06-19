package com.brycen.hrm.serivce.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.brycen.hrm.config.JwtTokenProvider;
import com.brycen.hrm.model.EmployeeEntity;
import com.brycen.hrm.model.security.CurrentUser;
import com.brycen.hrm.repository.EmployeeRepository;
import com.brycen.hrm.request.DepartmentRequest;
import com.brycen.hrm.request.EmployeeRequest;
import com.brycen.hrm.request.ProjectRequest;
import com.brycen.hrm.request.UserRequest;
import com.brycen.hrm.response.Response;
import com.brycen.hrm.response.object.EmployeeResponse;
import com.brycen.hrm.response.object.UserResponse;
import com.brycen.hrm.service.EmployeeService;
import com.brycen.hrm.status.BaseConvert;
import com.brycen.hrm.status.Code;

@Service
public class EmployeeImpl implements EmployeeService {

    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private BaseConvert baseConvert;
    
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    /* (non-Javadoc)
     * @see com.brycen.hrm.service.UserService#login(com.brycen.hrm.request.UserRequest)
     * 
     * Nếu có kết quả thì tạo jwt chèn vào trong data của response và return
     * Ngược lại sẽ trả về lỗi 100
     */
    @Override
    public Response login(UserRequest userRequest) {
       Response response = new Response();
       try {
           String jwt = "";
           
           // Kiếm tra có tồn tại username không?
           Authentication authentication = 
                   authenticationManager.authenticate(
                           new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
           
           // Lấy UserEntity convert to UserResponse
           EmployeeEntity entity = employeeRepo.findByUsername(userRequest.getUsername());
           UserResponse userResponse = baseConvert.userToResponse(entity);
           
           // Nếu có thì sẽ tạo jwt
           jwt = jwtTokenProvider.generateToken((CurrentUser) authentication.getPrincipal(), userResponse);
           
           response.setData(jwt);          
        } catch (Exception e) {
            response.setCode(Code.login_fail);
            response.setMessage(e.getMessage());
        }
        return response;
    }
    
    
    
    @Override
    public Response getEmployee(EmployeeRequest employeeRequest) {
        Response response = new Response();
        try {
            String select = "SELECT DISTINCT  * ";
            String from = "FROM employee ";
            String where = "WHERE employee.isdelete = 0"; 
            
            String name = employeeRequest.getName();
            if(name != null) {
                where += " and employee.name like '%" + name + "%'";
            }
            
            // Role department
            DepartmentRequest reqDepartment = employeeRequest.getDepartment();
            if(reqDepartment != null) {
                from += ", department ";
                where += " and employee.departmentid = department.id and department.id = " + reqDepartment.getId();
            }
            
            // Role department
            ProjectRequest reqProject = employeeRequest.getProject();
            if(reqProject != null) {
                from += ", project ";
                where += " and employee.projectid = project.id and project.id = " + reqProject.getId();
            }
            
            String nativeQuery = select + from + where;
            @SuppressWarnings("unchecked")
            Query<EmployeeEntity> query = (Query<EmployeeEntity>) entityManager.createNativeQuery(nativeQuery, EmployeeEntity.class);

            List<EmployeeEntity> list = query.getResultList();

            // Kết quả trả về
            List<EmployeeResponse> listEmpReponse = new ArrayList<EmployeeResponse>();
            for (EmployeeEntity employeeEntity : list) {
                EmployeeResponse empResponse = baseConvert.employeeToResponse(employeeEntity);
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
