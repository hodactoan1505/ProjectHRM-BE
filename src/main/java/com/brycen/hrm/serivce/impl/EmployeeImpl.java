package com.brycen.hrm.serivce.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.brycen.hrm.config.JwtTokenProvider;
import com.brycen.hrm.model.DepartmentEntity;
import com.brycen.hrm.model.EmployeeEntity;
import com.brycen.hrm.model.EmployeeSkillEntity;
import com.brycen.hrm.model.ProjectEntity;
import com.brycen.hrm.model.SkillEntity;
import com.brycen.hrm.model.security.CurrentUser;
import com.brycen.hrm.repository.DepartmentRepository;
import com.brycen.hrm.repository.EmployeeRepository;
import com.brycen.hrm.repository.EmployeeSkillRepository;
import com.brycen.hrm.repository.ProjectRepository;
import com.brycen.hrm.repository.SkillRepository;
import com.brycen.hrm.request.DepartmentRequest;
import com.brycen.hrm.request.EmployeeRequest;
import com.brycen.hrm.request.ProjectRequest;
import com.brycen.hrm.request.SkillRequest;
import com.brycen.hrm.request.UserRequest;
import com.brycen.hrm.response.Response;
import com.brycen.hrm.response.object.EmployeeResponse;
import com.brycen.hrm.response.object.UserResponse;
import com.brycen.hrm.service.EmployeeService;
import com.brycen.hrm.status.BaseConvert;
import com.brycen.hrm.status.Code;
import com.brycen.hrm.status.Message;
import com.google.gson.Gson;

@Service
public class EmployeeImpl implements EmployeeService {
    private static final Logger logger = Logger.getLogger(EmployeeImpl.class);
    
    private Gson gson = new Gson();
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
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private EmployeeSkillRepository employeeSkillRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.brycen.hrm.service.UserService#login(com.brycen.hrm.request.UserRequest)
	 * 
	 * Nếu có kết quả thì tạo jwt chèn vào trong data của response và return Ngược
	 * lại sẽ trả về lỗi 100
	 */
	@Override
	public Response login(UserRequest userRequest) {
		Response response = new Response();
		try {
			String jwt = "";

			// Kiếm tra có tồn tại username không?
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));

			// Lấy UserEntity convert to UserResponse
			EmployeeEntity entity = employeeRepo.findByUsername(userRequest.getUsername());
			UserResponse userResponse = baseConvert.userToResponse(entity);

			// Nếu có thì sẽ tạo jwt
			jwt = jwtTokenProvider.generateToken((CurrentUser) authentication.getPrincipal(), userResponse);

			response.setData(jwt);
			logger.info("login => [Request]" + gson.toJson(userRequest)+ "[Response]"+ gson.toJson(response));
		} catch (Exception e) {
			response.setCode(Code.login_fail);
			response.setMessage(e.getMessage());
			logger.error("login =>" + gson.toJson(response));
		}
		return response;
	}

	@Override
	public Response getEmployee(EmployeeRequest employeeRequest) {
		Response response = new Response();
		try {
			String select = " SELECT DISTINCT  * ";
			String from = " FROM employee ";
			String where = " WHERE employee.isdelete = 0 ";
			
			int id = employeeRequest.getId();
			if(id != 0) {
			    where += " and employee.id = " + id;
			}
			
			String name = employeeRequest.getName();
			if (name != null) {
				where += " and employee.name like '%" + name + "%'";
			}

			// Tìm kiếm theo department
			List<DepartmentRequest> reqDepartment = employeeRequest.getDepartment();
			if (reqDepartment != null && !reqDepartment.isEmpty()) {
				from += ", department ";
				where += " and employee.departmentid = department.id "
						+ " and department.id = " + reqDepartment.get(0).getId();
			}

			// Tìm kiếm theo project
			List<ProjectRequest> reqProject = employeeRequest.getProject();
			if (reqProject != null && !reqProject.isEmpty()) {
				from += ", project ";
				where += " and employee.projectid = project.id "
						+ " and project.id = " + reqProject.get(0).getId();
			}
			
			// Lấy danh sách skill
			List<SkillRequest> skills = employeeRequest.getSkills();
			if(skills != null && !skills.isEmpty()) {
				from += ", skill, employeeskill ";
				where += " and employeeskill.skillid = skill.id "
						+ " and employee.id = employeeskill.employeeid ";
				
				for (SkillRequest skillRequest : skills) {
					where += " and skill.id = " + skillRequest.getId();
				}
			}
			
			String nativeQuery = select + from + where;
			@SuppressWarnings("unchecked")
			Query<EmployeeEntity> query = (Query<EmployeeEntity>) entityManager.createNativeQuery(nativeQuery,
					EmployeeEntity.class);

			List<EmployeeEntity> list = query.getResultList();

			// Kết quả trả về
			List<EmployeeResponse> listEmpReponse = new ArrayList<EmployeeResponse>();
			for (EmployeeEntity employeeEntity : list) {
				EmployeeResponse empResponse = baseConvert.employeeToResponse(employeeEntity);
				listEmpReponse.add(empResponse);
			}

			response.setData(listEmpReponse);
			logger.info(SecurityContextHolder.getContext().getAuthentication().getName() +  " getEmployee => [Request]"+gson.toJson(employeeRequest)+"[Reponse]" + gson.toJson(response));
		} catch (Exception e) {
			response.setCode(Code.unknown);
			response.setMessage(e.getMessage());
			logger.error("getEmployee =>" + gson.toJson(response));
		}
		return response;
	}

	@Override
	public Response addEmployee(EmployeeRequest employeeRequest) {
		Response response = new Response();
		try {
			EmployeeEntity entity = new EmployeeEntity();
			
			// Kiểm tra tên có rỗng hay không
			if (employeeRequest.getName() == null || employeeRequest.getName().trim() == "") {
				response.setCode(Code.name_not_empty);
				response.setMessage(Message.name_not_empty);
				return response;
			}

			// Kiểm tra kĩ năng
			if (employeeRequest.getSkills() == null || employeeRequest.getSkills().isEmpty()) {
				response.setCode(Code.skill_not_empty);
				response.setMessage(Message.skill_not_empty);
				return response;
			}
			
			// Kiểm tra danh sách kĩ năng có tồn tại không
			List<SkillEntity> dbSkill = skillRepository.findAll();
			for (SkillRequest resSkill : employeeRequest.getSkills()) {
				boolean flag = false;
				for (SkillEntity skillentity : dbSkill) {
					if(resSkill.getId() == skillentity.getId()) {
						flag = true;
						break;
					}
					
				}
				if(!flag) {
					response.setCode(Code.skill_not_found);
					response.setMessage(resSkill.getName() + " " + Message.skill_not_found);
					return response;
				}
			}
			

			// Kiểm tra bộ phận
			if (employeeRequest.getDepartment() == null) {
				response.setCode(Code.department_not_empty);
				response.setMessage(Message.department_not_empty);
				return response;
			}
			
			// Kiểm tra phòng ban có tồn tại hay không ?
			int departmentId = employeeRequest.getDepartment().get(0).getId();
			Optional<DepartmentEntity> oDepartment = departmentRepository.findById(departmentId);
			if(oDepartment.orElse(null) == null) {
				response.setCode(Code.department_not_found);
				response.setMessage(Message.department_not_found);
				return response;
			}
			
			// Kiểm tra dự án
			if (employeeRequest.getProject() == null) {
				response.setCode(Code.project_not_empty);
				response.setMessage(Message.project_not_empty);
				return response;
			}
			
			// Kiểm tra dự án có tồn tại hay không ?
			int projectId = employeeRequest.getProject().get(0).getId();
			Optional<ProjectEntity> pOptional = projectRepository.findById(projectId);
			if(pOptional.orElse(null) == null) {
				response.setCode(Code.project_not_found);
				response.setMessage(Message.project_not_found);
				return response;
			}
			
			entity.setName(employeeRequest.getName());
			entity.setExperience(employeeRequest.getExperience());
			entity.setAddress(employeeRequest.getAddress());
			entity.setPhone(employeeRequest.getPhone());
			entity.setGmail(employeeRequest.getGmail());
			entity.setSkype(employeeRequest.getSkype());
			entity.setQueQuan(employeeRequest.getQueQuan());
			entity.setAvatar(employeeRequest.getAvatar());
			entity.setSex(employeeRequest.isSex());
			entity.setMarried(employeeRequest.isMarried());
			entity.setDepartment(oDepartment.get());
			entity.setProject(pOptional.get());
			
			// Kiểm tra ngày sinh có null không
			if(employeeRequest.getBirthday() != null) {
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = sdf1.parse(employeeRequest.getBirthday());
				java.sql.Date birthday = new java.sql.Date(date.getTime());
				entity.setBirthday(birthday.toString());
			}
			
			// Kiểm tra ngày vào công ty có null không
			if(employeeRequest.getJoinCompany() != null) {
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = sdf1.parse(employeeRequest.getJoinCompany());
				java.sql.Date joinCompany = new java.sql.Date(date.getTime());
				entity.setJoinCompany(joinCompany.toString());
			}
			
			// Kiểm tra ngày hết hạn thử việc có null không
			if(employeeRequest.getFinishTraning() != null) {
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = sdf1.parse(employeeRequest.getFinishTraning());
				java.sql.Date finishTraning = new java.sql.Date(date.getTime());
				entity.setFinishTraning(finishTraning.toString());
			}
			
			employeeRepo.save(entity);
			
			// Set danh sách kĩ năng
			for (SkillRequest request : employeeRequest.getSkills()) {
				for (SkillEntity skillEntity : dbSkill) {
					if(request.getId() == skillEntity.getId()) {
						EmployeeSkillEntity empskill = new EmployeeSkillEntity();
						empskill.setEmployee(entity);
						empskill.setSkill(skillEntity);
						
						employeeSkillRepository.save(empskill);
					}
				}
			}
			logger.info(SecurityContextHolder.getContext().getAuthentication().getName() + " addEmployee => [Request]"+gson.toJson(employeeRequest)+"[Reponse]" + gson.toJson(response));
			
		} catch (Exception e) {
			response.setCode(Code.unknown);
			response.setMessage(e.getMessage());
			logger.error(SecurityContextHolder.getContext().getAuthentication().getName() + " addEmployee => " + gson.toJson(response));
		}
		return response;
	}

    @Override
    public Response updateEmployee(EmployeeRequest employeeRequest) {
        Response response = new Response();
        try {
             Optional<EmployeeEntity> eOptional = employeeRepo.findById(employeeRequest.getId());
             
             if(eOptional.orElse(null) == null) {
                 response.setCode(Code.employee_not_found);
                 response.setMessage(Message.employee_not_found);
                 return response;
             }
             
             EmployeeEntity entity = eOptional.get();
            
            // Kiểm tra tên có rỗng hay không
            if (employeeRequest.getName() == null || employeeRequest.getName().trim() == "") {
                response.setCode(Code.name_not_empty);
                response.setMessage(Message.name_not_empty);
                return response;
            }

            // Kiểm tra kĩ năng
            if (employeeRequest.getSkills() == null || employeeRequest.getSkills().isEmpty()) {
                response.setCode(Code.skill_not_empty);
                response.setMessage(Message.skill_not_empty);
                return response;
            }
            
            // Kiểm tra danh sách kĩ năng có tồn tại không
            List<SkillEntity> dbSkill = skillRepository.findAll();
            for (SkillRequest resSkill : employeeRequest.getSkills()) {
                boolean flag = false;
                for (SkillEntity skillentity : dbSkill) {
                    if(resSkill.getId() == skillentity.getId()) {
                        flag = true;
                        break;
                    }
                    
                }
                if(!flag) {
                    response.setCode(Code.skill_not_found);
                    response.setMessage(resSkill.getName() + " " + Message.skill_not_found);
                    return response;
                }
            }
            

            // Kiểm tra bộ phận
            if (employeeRequest.getDepartment() == null) {
                response.setCode(Code.department_not_empty);
                response.setMessage(Message.department_not_empty);
                return response;
            }
            
            // Kiểm tra phòng ban có tồn tại hay không ?
            int departmentId = employeeRequest.getDepartment().get(0).getId();
            Optional<DepartmentEntity> oDepartment = departmentRepository.findById(departmentId);
            if(oDepartment.orElse(null) == null) {
                response.setCode(Code.department_not_found);
                response.setMessage(Message.department_not_found);
                return response;
            }
            
            // Kiểm tra dự án
            if (employeeRequest.getProject() == null) {
                response.setCode(Code.project_not_empty);
                response.setMessage(Message.project_not_empty);
                return response;
            }
            
            // Kiểm tra dự án có tồn tại hay không ?
            int projectId = employeeRequest.getProject().get(0).getId();
            Optional<ProjectEntity> pOptional = projectRepository.findById(projectId);
            if(pOptional.orElse(null) == null) {
                response.setCode(Code.project_not_found);
                response.setMessage(Message.project_not_found);
                return response;
            }
            
            entity.setName(employeeRequest.getName());
            entity.setExperience(employeeRequest.getExperience());
            entity.setAddress(employeeRequest.getAddress());
            entity.setPhone(employeeRequest.getPhone());
            entity.setGmail(employeeRequest.getGmail());
            entity.setSkype(employeeRequest.getSkype());
            entity.setQueQuan(employeeRequest.getQueQuan());
            entity.setAvatar(employeeRequest.getAvatar());
            entity.setSex(employeeRequest.isSex());
            entity.setMarried(employeeRequest.isMarried());
            entity.setDepartment(oDepartment.get());
            entity.setProject(pOptional.get());
            
            // Kiểm tra ngày sinh có null không
            if(employeeRequest.getBirthday() != null) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf1.parse(employeeRequest.getBirthday());
                java.sql.Date birthday = new java.sql.Date(date.getTime());
                entity.setBirthday(birthday.toString());
            }
            
            // Kiểm tra ngày vào công ty có null không
            if(employeeRequest.getJoinCompany() != null) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf1.parse(employeeRequest.getJoinCompany());
                java.sql.Date joinCompany = new java.sql.Date(date.getTime());
                entity.setJoinCompany(joinCompany.toString());
            }
            
            // Kiểm tra ngày hết hạn thử việc có null không
            if(employeeRequest.getFinishTraning() != null) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf1.parse(employeeRequest.getFinishTraning());
                java.sql.Date finishTraning = new java.sql.Date(date.getTime());
                entity.setFinishTraning(finishTraning.toString());
            }
            
            // Lưu thông tin thay đổi
            employeeRepo.save(entity);
            
            // Xóa các kĩ năng cũ
            employeeSkillRepository.deleteSkillofEmp(entity.getId());
            
            // Set danh sách kĩ năng
            for (SkillRequest request : employeeRequest.getSkills()) {
                for (SkillEntity skillEntity : dbSkill) {
                    if(request.getId() == skillEntity.getId()) {
                        EmployeeSkillEntity empskill = new EmployeeSkillEntity();
                        empskill.setEmployee(entity);
                        empskill.setSkill(skillEntity);
                        
                        employeeSkillRepository.save(empskill);
                    }
                }
            }
            logger.info(SecurityContextHolder.getContext().getAuthentication().getName() + " updateEmployee => [Request]"+gson.toJson(employeeRequest)+"[Reponse]" + gson.toJson(response));
        } catch (Exception e) {
            response.setCode(Code.unknown);
            response.setMessage(e.getMessage());
            logger.error(SecurityContextHolder.getContext().getAuthentication().getName() + " updateEmployee => " + gson.toJson(response));
        }
        return response;
    }

    @Override
    @Transactional
    public Response deleteEmployee(List<Integer> list) {
        Response response = new Response();
        try {
            
            if(list.isEmpty()) {
                response.setCode(Code.list_employee_id_empty);
                response.setMessage(Message.list_employee_id_empty);
                return response;
            }
            
            String update = " UPDATE employee ";
            String set = " SET employee.isdelete = 1 ";
            String where = " ";
            for (int i = 0; i < list.size(); i++) {
                if(i == 0) {
                    where += " WHERE employee.id = " + list.get(0);
                }else {
                    where += " and employee.id " + list.get(i);
                }
            }
            
            String nativeQuery = update + set + where;
            entityManager.createNativeQuery(nativeQuery,EmployeeEntity.class).executeUpdate();            
            logger.info(SecurityContextHolder.getContext().getAuthentication().getName() + " deleteEmployee => [Request]"+gson.toJson(list)+"[Reponse]" + gson.toJson(response));
        } catch (Exception e) {
            response.setCode(Code.unknown);
            response.setMessage(e.getMessage());
            logger.error(SecurityContextHolder.getContext().getAuthentication().getName() + " deleteEmployee => " + gson.toJson(response));
        }
        return response;
    }
	
	
	
	
}
