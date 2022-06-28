package com.kosta.dto;

import java.sql.Date;

//CRUD작업(insert(C), select(R),update(U), Delete(D)==>DAO
//DAO작업완료후 Service에 전달할때 return은 1개만 가능 ...여러 값을 묶음(class로 정의하고 object를 만들어서return)
//VO(Value Object) : 값을 담는 그릇
//DTO(Data Transfer Object) : data를 담아서 전송하기 위한 객체 
public class EmpVO {
	// field,default constructor, getter/setter
	private int employee_id;// 2147483647(int), 32767(short)
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	private String job_id;
	private double salary;
	private double commission_pct;
	private int manager_id;
	private int department_id;

	
	
	public EmpVO() {
		super();
	}

	public EmpVO(int employee_id, String first_name, String last_name) {
		super();
		this.employee_id = employee_id;
		this.first_name = first_name;
		this.last_name = last_name;
	}






	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	public String getJob_id() {
		return job_id;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getCommission_pct() {
		return commission_pct;
	}

	public void setCommission_pct(double commission_pct) {
		this.commission_pct = commission_pct;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmpVO [employee_id=").append(employee_id).append(", first_name=").append(first_name)
				.append(", last_name=").append(last_name).append(", email=").append(email).append(", phone_number=")
				.append(phone_number).append(", hire_date=").append(hire_date).append(", job_id=").append(job_id)
				.append(", salary=").append(salary).append(", commission_pct=").append(commission_pct)
				.append(", manager_id=").append(manager_id).append(", department_id=").append(department_id)
				.append("]");
		return builder.toString();
	}

}
