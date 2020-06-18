package com.brycen.hrm.response;

import com.brycen.hrm.status.Code;
import com.brycen.hrm.status.Message;

/**
 * [Description]:Body trả về cho client
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class Response {
    
    /**
     * Trạng thái
     */
    private int code = Code.success;
    
    /**
     * Tin nhắn trả về
     */
    private String message = Message.success;
    
    /**
     * Đối tượng trả về
     */
    private Object data = null;
    
    public Response() {
        
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    
}
