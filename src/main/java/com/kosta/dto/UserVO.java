package com.kosta.dto;

public class UserVO {
	String user_id;
	String user_name;
	String user_pass;
	String phone;
	public UserVO() {
		super();
	}
	public UserVO(String user_id, String user_name, String user_pass, String phone) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_pass = user_pass;
		this.phone = phone;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserVO [user_id=").append(user_id).append(", user_name=").append(user_name)
				.append(", user_pass=").append(user_pass).append(", phone=").append(phone).append("]");
		return builder.toString();
	}

	
	
}
