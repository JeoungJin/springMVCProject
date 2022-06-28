package com.kosta.dto;

public class BoardEmpVO {

	private int bno;
	private String title;
	private String content;
	private String fullname;
	public BoardEmpVO(int bno, String title, String content, String fullname) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.fullname = fullname;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardEmpVO [bno=").append(bno).append(", title=").append(title).append(", content=")
				.append(content).append(", fullname=").append(fullname).append("]");
		return builder.toString();
	}
	
	
	
}


