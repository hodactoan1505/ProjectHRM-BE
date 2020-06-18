package com.brycen.hrm.status;

/**
 * [Description]:Description all status code response to client
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class Code {
    
    /**
     * Trả về khi các hành động thành công
     * Return code 999
     */
    public static int success = 999;
    
    /**
     * Trả về khi login bị thất bại
     * - Username, password chưa đúng
     */
    public static int login_fail = 100;
    
    /**
     * Trả về khi bắn ra exception không biết
     */
    public static int unknown = 998;
    
    
    /**
     * Trả về khi user không tồn tại
     */
    public static int user_not_found = 110;
}
