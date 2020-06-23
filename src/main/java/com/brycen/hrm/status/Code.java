package com.brycen.hrm.status;

/**
 * [Description]:Description all status code response to client [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class Code {
	/**
	 * Các dãy code 990 -> 999 : Code thành công, các lỗi không xác định, login...
	 * 110 - 119 : Code về các đối tượng không tồn tại 120 - 129 : Code về các đối
	 * tượng đã tồn tại 130 - 139 : Code về các đối tượng null, empty
	 */

	/**
	 * Trả về khi các hành động thành công Return code 999
	 */
	public static int success = 999;

	/**
	 * Trả về khi login bị thất bại - Username, password chưa đúng
	 */
	public static int login_fail = 997;

	/**
	 * Trả về khi bắn ra exception không biết
	 */
	public static int unknown = 998;

	/**
	 * Trả về khi user không tồn tại
	 */
	public static int user_not_found = 110;

	/**
	 * Trả về khi phòng ban không tồn tại
	 */
	public static int department_not_found = 111;
	
	/**
	 * Trả về khi skill không tồn tại
	 */
	public static int skill_not_found = 112;
	
	/**
	 * Trả về khi dự án không tồn tại
	 */
	public static int project_not_found = 113;
	
	 /**
     * Trả về khi dự án không tồn tại
     */
    public static int employee_not_found = 114;
	
	/**
	 * Trả về khi họ tên bằng null hoặc rỗng
	 */
	public static int name_not_empty = 130;

	/**
	 * Trả về khi danh sách kĩ năng rỗng
	 */
	public static int skill_not_empty = 131;

	/**
	 * Trả về khi phòng ban rỗng
	 */
	public static int department_not_empty = 132;
	
	/**
	 * Trả về khi dự án rỗng
	 */
	public static int project_not_empty = 133;
	
	/**
	 * Trả về khi danh sách id nhân viên rỗng => hành động xóa
	 */
	public static int list_employee_id_empty = 134;
	
	
	
	public static int skill_already_exist = 141;
	public static int department_already_exist = 142;
}
