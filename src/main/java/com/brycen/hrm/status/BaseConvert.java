package com.brycen.hrm.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brycen.hrm.model.DepartmentEntity;
import com.brycen.hrm.model.EmployeeEntity;
import com.brycen.hrm.model.EmployeeSkillEntity;
import com.brycen.hrm.model.ProjectEntity;
import com.brycen.hrm.model.RoleEntity;
import com.brycen.hrm.model.SkillEntity;
import com.brycen.hrm.repository.SkillRepository;
import com.brycen.hrm.request.DepartmentRequest;
import com.brycen.hrm.response.object.DepartmentResponse;
import com.brycen.hrm.response.object.EmployeeResponse;
import com.brycen.hrm.response.object.ProjectResponse;
import com.brycen.hrm.response.object.RoleResponse;
import com.brycen.hrm.response.object.SkillResponse;
import com.brycen.hrm.response.object.UserResponse;

/**
 * [Description]:Class chứa các function convert object [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Component
public class BaseConvert {

    @Autowired
    private SkillRepository skillRepository;

    /**
     * [Description]:Function convert roleEntity => roleReponse [ Remarks ]:<br/>
     *
     * @return RoleResponse
     */
    public RoleResponse roleToResponse(RoleEntity entity) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(entity.getId());
        roleResponse.setName(entity.getName());
        roleResponse.setScreen(entity.getScreen());

        // Covert Base entity => Response
        roleResponse.setCreateDate(entity.getCreateDate().toString());
        roleResponse.setCreateBy(entity.getCreateBy());
        roleResponse.setLastModifiedBy(entity.getLastModifiedBy());
        roleResponse.setLastModifiedDate(entity.getLastModifiedDate().toString());

        return roleResponse;
    }

    /**
     * [Description]:Function convert UserEntity => UserResponse [ Remarks ]:<br/>
     *
     * @param userEntity
     * @return
     */
    public UserResponse userToResponse(EmployeeEntity entity) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(entity.getUsername());
        userResponse.setName(entity.getName());
        userResponse.setId(entity.getId());

        // set Department
        DepartmentResponse resDepartment = new DepartmentResponse();
        resDepartment = departmentToResponse(entity.getDepartment());
        userResponse.setDepartment(resDepartment);

        // set Project
        ProjectResponse resProject = new ProjectResponse();
        resProject = projectToResponse(entity.getProject());
        userResponse.setProject(resProject);

        // Convert Entity => roleResponse
        RoleResponse roleResponse = roleToResponse(entity.getRole());
        userResponse.setRole(roleResponse);

        return userResponse;
    }

    /**
     * [Description]:Convert DepartmentEntity to response [ Remarks ]:<br/>
     *
     * @param departmentEntity
     * @return
     */
    public DepartmentResponse departmentToResponse(DepartmentEntity departmentEntity) {
        DepartmentResponse resDepartment = new DepartmentResponse();
        resDepartment.setId(departmentEntity.getId());
        resDepartment.setName(departmentEntity.getName());
        resDepartment.setStartdate(departmentEntity.getStartDate());
        resDepartment.setDescription(departmentEntity.getDescription());
        resDepartment.setPersions(departmentEntity.getEmployees().size());

        // Covert Base entity => Response
        resDepartment.setCreateDate(departmentEntity.getCreateDate().toString());
        resDepartment.setCreateBy(departmentEntity.getCreateBy());
        resDepartment.setLastModifiedBy(departmentEntity.getLastModifiedBy());
        resDepartment.setLastModifiedDate(departmentEntity.getLastModifiedDate().toString());

        return resDepartment;
    }

    /**
     * [Description]:Convert Project Entity => Response [ Remarks ]:<br/>
     *
     * @param entity
     * @return
     */
    public ProjectResponse projectToResponse(ProjectEntity entity) {
        ProjectResponse response = new ProjectResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setStartdate(entity.getStartDate());
        response.setEnddate(entity.getEndDate());
        response.setDescription(entity.getDescription());
        response.setPersion(entity.getEmployees().size());

        // Covert Department Entity => Response
        DepartmentResponse resDepartment = new DepartmentResponse();
        resDepartment = departmentToResponse(entity.getDepartment());
        response.setDepartment(resDepartment);

        // Covert Base entity => Response
        response.setCreateDate(entity.getCreateDate().toString());
        response.setCreateBy(entity.getCreateBy());
        response.setLastModifiedBy(entity.getLastModifiedBy());
        response.setLastModifiedDate(entity.getLastModifiedDate().toString());

        return response;
    }

    // Chuyển đổi skill Enity => Response
    public SkillResponse skillToRespose(SkillEntity entity) {
        SkillResponse response = new SkillResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setCreateBy(entity.getCreateBy());
        response.setCreateDate(entity.getCreateDate().toString());
        response.setLastModifiedBy(entity.getLastModifiedBy());
        response.setLastModifiedDate(entity.getLastModifiedDate().toString());

        return response;
    }

    public List<SkillResponse> employeeSkillToListSkillResponse(List<EmployeeSkillEntity> list) {
        List<SkillResponse> listResSkill = new ArrayList<SkillResponse>();
        // Lấy danh sách tất cả skill
        List<SkillEntity> skills = skillRepository.findAll();
        /*
         * So sánh trong tất cả skill. Nếu tồn tại thì convert sang Response
         */

        for (EmployeeSkillEntity skillOfEmployee : list) {
            for (SkillEntity entity : skills) {
                if (skillOfEmployee.getSkill().equals(entity)) {
                    SkillResponse response = skillToRespose(entity);
                    listResSkill.add(response);
                    break;
                }
            }
        }
        return listResSkill;
    }

    /**
     * [Description]:Convert Employee Entity => Response [ Remarks ]:<br/>
     *
     * @param entity
     * @return
     */
    public EmployeeResponse employeeToResponse(EmployeeEntity entity) {

        EmployeeResponse response = new EmployeeResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setPhone(entity.getPhone());
        response.setGmail(entity.getGmail());
        response.setSkype(entity.getSkype());
        response.setAddress(entity.getAddress());
        response.setBirthday((entity.getBirthday() != null) ? entity.getBirthday().toString() : null);
        response.setExperience(entity.getExperience());
        response.setQueQuan(entity.getQueQuan());
        response.setAvatar(entity.getAvatar());
        response.setSex(entity.isSex());
        response.setMarried(entity.isMarried());
        response.setFinishTraning((entity.getFinishTraning() != null) ? entity.getFinishTraning().toString() : null);
        response.setJoinCompany((entity.getJoinCompany() != null) ? entity.getJoinCompany().toString() : null);

        // set Department
        List<DepartmentResponse> listDepartment = new ArrayList<DepartmentResponse>();
        DepartmentResponse resDepartment = new DepartmentResponse();
        resDepartment = departmentToResponse(entity.getDepartment());
        listDepartment.add(resDepartment);
        response.setDepartment(listDepartment);

        // set Project
        List<ProjectResponse> listProject = new ArrayList<ProjectResponse>();
        ProjectResponse resProject = new ProjectResponse();
        resProject = projectToResponse(entity.getProject());
        listProject.add(resProject);
        response.setProject(listProject);

        // set Skill
        List<SkillResponse> listSkill = new ArrayList<SkillResponse>();
        listSkill = employeeSkillToListSkillResponse(entity.getSkills());
        response.setSkills(listSkill);

        // Covert Base entity => Response
        response.setCreateDate(entity.getCreateDate().toString());
        response.setCreateBy(entity.getCreateBy());
        response.setLastModifiedBy(entity.getLastModifiedBy());
        response.setLastModifiedDate(entity.getLastModifiedDate().toString());

        return response;
    }

    public DepartmentEntity departmentRequestToEntity(DepartmentRequest request) {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setName(request.getName());
        entity.setStartDate(request.getStartdate());
        entity.setDescription(request.getDescription());

        return entity;
    }
}
