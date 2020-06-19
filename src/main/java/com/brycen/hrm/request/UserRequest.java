package com.brycen.hrm.request;

import com.brycen.hrm.response.object.BaseResponse;

/**
 * [Description]:Đối tượng UserRequest được gửi lên Server
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class UserRequest {
    private String username;
    private String password;
    
    public UserRequest() {
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
