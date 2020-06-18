package com.brycen.hrm.response.object;

public class UserResponse extends BaseResponse{
    private String username;
    private RoleResponse role;
    private int employeeid;

    public UserResponse() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleResponse getRole() {
        return role;
    }

    public void setRole(RoleResponse role) {
        this.role = role;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

   

}
