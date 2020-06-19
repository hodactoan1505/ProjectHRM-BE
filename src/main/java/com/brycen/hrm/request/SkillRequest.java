package com.brycen.hrm.request;

public class SkillRequest {
    private int id;
    private String name;
    private boolean skilled;
    
    public SkillRequest() {
        
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

    public boolean isSkilled() {
        return skilled;
    }

    public void setSkilled(boolean skilled) {
        this.skilled = skilled;
    }
    
    
}
