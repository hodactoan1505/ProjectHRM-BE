package com.brycen.hrm.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "employee")
public class EmployeeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String birthday;
    private String phone;
    private String gmail;
    private String skype;
    private String experience;
    private String queQuan;
    private String username;
    private String password;

    @Lob
    private String avatar;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean sex;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean married;

    @Column(name = "finishtraning")
    private String finishTraning;

    @Column(name = "joincompany")
    private String joinCompany;

    @ManyToOne
    @JoinColumn(name = "departmentid")
    private DepartmentEntity department;

    @ManyToOne
    @JoinColumn(name = "projectid")
    private ProjectEntity project;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeSkillEntity> skills;

    @OneToOne
    @JoinColumn(name = "roleid")
    private RoleEntity role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFinishTraning() {
        return finishTraning;
    }

    public void setFinishTraning(String finishTraning) {
        this.finishTraning = finishTraning;
    }

    public String getJoinCompany() {
        return joinCompany;
    }

    public void setJoinCompany(String joinCompany) {
        this.joinCompany = joinCompany;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public List<EmployeeSkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(List<EmployeeSkillEntity> skills) {
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeEntity() {

    }
}
