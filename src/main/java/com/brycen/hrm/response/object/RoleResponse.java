package com.brycen.hrm.response.object;

public class RoleResponse extends BaseResponse{
    private int id;
    private String name;
    private String screen;

    public RoleResponse() {

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

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

}
