package com.brycen.hrm.response.object;

public class DepartmentResponse extends BaseResponse {
    private int id;
    private String name;
    private String description;
    private String startdate;
    private int persions;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public int getPersions() {
        return persions;
    }

    public void setPersions(int persions) {
        this.persions = persions;
    }

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
