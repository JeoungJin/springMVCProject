package com.kosta.dto;

//DTO(Data Transfer Object):data전송시 사용하는 틀이다는 의미 
//VO(Value Object)
//Beans(object)
public class DeptDTO {
	private int department_id;
	private String department_name;
	private int  manager_id;
	private int location_id;
	public DeptDTO() {
		//자바관련기술들(jsp/servlet, spring, mybatis등에서 자동으로 객체생성시 default생성자가 사용된다.)
	}
	public DeptDTO(int department_id, String department_name, int manager_id, int location_id) {
		super();
		this.department_id = department_id;
		this.department_name = department_name;
		this.manager_id = manager_id;
		this.location_id = location_id;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeptDTO [department_id=").append(department_id).append(", department_name=")
				.append(department_name).append(", manager_id=").append(manager_id).append(", location_id=")
				.append(location_id).append("]");
		return builder.toString();
	}
	
	
}
