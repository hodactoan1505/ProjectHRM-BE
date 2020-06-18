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
import com.brycen.hrm.model.UserEntity;
import com.brycen.hrm.repository.SkillRepository;
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
    public UserResponse userToResponse(UserEntity userEntity) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(userEntity.getUsername());
        userResponse.setEmployeeid(userEntity.getEmployee().getId());

        // Convert Entity => roleResponse
        RoleResponse roleResponse = roleToResponse(userEntity.getRole());
        userResponse.setRole(roleResponse);

        // Covert Base entity => Response
        userResponse.setCreateDate(userEntity.getCreateDate().toString());
        userResponse.setCreateBy(userEntity.getCreateBy());
        userResponse.setLastModifiedBy(userEntity.getLastModifiedBy());
        userResponse.setLastModifiedDate(userEntity.getLastModifiedDate().toString());
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

        // Covert Base entity => Response
        response.setCreateDate(entity.getCreateDate().toString());
        response.setCreateBy(entity.getCreateBy());
        response.setLastModifiedBy(entity.getLastModifiedBy());
        response.setLastModifiedDate(entity.getLastModifiedDate().toString());

        return response;
    }

    public List<SkillResponse> skillEntityToResponse(List<EmployeeSkillEntity> list) {
        List<SkillResponse> listResSkill = new ArrayList<SkillResponse>();
        // Lấy tất cả skill
        List<SkillEntity> listEntity = skillRepository.findAll();

        /*
         * Tìm kiếm trong danh sách kĩ năng của Employee Nếu có thì sẽ đánh dấu skilled bằng true
         * 
         * Kết quả sẽ trả về danh sách toàn bộ skill nhưng có đánh dấu là Employee biết kĩ năng nào
         */
        for (SkillEntity skillEntity : listEntity) {
            boolean flag = false;
            for (EmployeeSkillEntity entityOfEmp : list) {
                if (skillEntity.equals(entityOfEmp.getSkill())) {
                    flag = true;
                    break;
                }
            }

            SkillResponse response = new SkillResponse();
            response.setId(skillEntity.getId());
            response.setName(skillEntity.getName());
            response.setCreateBy(skillEntity.getCreateBy());
            response.setCreateDate(skillEntity.getCreateDate().toString());
            response.setLastModifiedBy(skillEntity.getLastModifiedBy());
            response.setLastModifiedDate(skillEntity.getLastModifiedDate().toString());

            if (flag) {
                response.setSkilled(true);
            } else {
                response.setSkilled(false);
            }

            listResSkill.add(response);
        }

        return listResSkill;
    }

    /**
     * [Description]:Convert Employee Entity => Response [ Remarks ]:<br/>
     *
     * @param entity
     * @return
     */
    public EmployeeResponse EmployeeToResponse(EmployeeEntity entity) {

        EmployeeResponse response = new EmployeeResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setPhone(entity.getPhone());
        response.setGmail(entity.getGmail());
        response.setSkype(entity.getSkype());

        // set Department
        DepartmentResponse resDepartment = new DepartmentResponse();
        resDepartment = departmentToResponse(entity.getDepartment());
        response.setDepartment(resDepartment);

        // set Project
        ProjectResponse resProject = new ProjectResponse();
        resProject = projectToResponse(entity.getProject());
        response.setProject(resProject);

        // set Skill
        List<SkillResponse> listSkill = new ArrayList<SkillResponse>();
        listSkill = skillEntityToResponse(entity.getSkills());
        response.setSkills(listSkill);

        return response;
    }

}
