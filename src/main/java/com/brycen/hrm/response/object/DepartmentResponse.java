package com.brycen.hrm.response.object;

public class DepartmentResponse extends BaseResponse {
    private int id;
    private String name;


    public DepartmentResponse() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
