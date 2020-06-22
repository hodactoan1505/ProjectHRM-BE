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
    
    /**
     * Trả về khi phòng ban không tồn tại
     */
    public static String department_not_found = "Department not found";
    
    /**
     * Trả về khi Skill không tồn tại
     */
    public static String skill_not_found = "Skill not found";
    
    /**
     * Trả về khi dự án không tồn tại
     */
    public static String project_not_found = "Project not found";
    
    
    /**
     * Trả về khi nhân viên không tồn tại
     */
    public static String employee_not_found = "Employee not found";
    
    /**
     * Trả về khi họ tên đang bị null hoặc rỗng
     */
    public static String name_not_empty = "Name not empty";
    
    /**
     * Trả về khi danh sách skill đang rỗng
     */
    public static String skill_not_empty = "Have at least one skill";
    
    /**
     * Trả về khi phòng ban đang rỗng
     */
    public static String department_not_empty = "Department not empty";
    
    /**
     * Trả về khi dự án đang rỗng
     */
    public static String project_not_empty = "Project not empty";
    
    /**
     * Trả về khi danh sách id của nhân viên xóa không có
     */
    public static String list_employee_id_empty = "Danh sách nhân viên xóa không có";
}
