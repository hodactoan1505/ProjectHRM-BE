package com.brycen.hrm.status;

/**
 * [Description]:Message return to client
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class Message {
    /**
     * Trả về khi thực hiện 1 hành động thành công
     * Return Success
     */
    public static String success = "Success";
    
    /**
     * Trả về khi bắn ra exception không biết
     */
    public static String unknown = "Unknown";
    
    
    /**
     * Trả về khi user không tồn tại
     */
    public static String user_not_found = "User not found";
}
