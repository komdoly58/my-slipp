package net.slipp.web;

public class User {
	private String UserId;
	private String password;
	private String name;
	private String email;
	
	public void setUserId(String userId) {
		UserId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [UserId=" + UserId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
	
	
}
