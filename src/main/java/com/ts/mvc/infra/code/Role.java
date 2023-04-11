package com.ts.mvc.infra.code;

public enum Role {
	
	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN"),
	PROFILEIMAGE("https://ifh.cc/g/xLrPyz.jpg");

	private String grade;
	
	Role(String grade) {
		this.grade = grade;
	}
	
	public String desc() {
		return this.grade;
	}

}
