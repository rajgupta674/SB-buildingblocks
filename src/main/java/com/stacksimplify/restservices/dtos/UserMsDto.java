package com.stacksimplify.restservices.dtos;

public class UserMsDto {

	private Long id;
	private String username;
	private String emailid;
	private String rolename;

	public UserMsDto() {

	}

	public UserMsDto(Long id, String username, String emailid) {
		super();
		this.id = id;
		this.username = username;
		this.emailid = emailid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
