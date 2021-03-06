package net.slipp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=20, unique=true)
	private String userId;
	private String password;
	private String name;
	private String email;
	
	
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public boolean matchId(Long newId) {
		if(newId == null) {
			return false;
		}
		return newId.equals(id);	
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean SetPassword(String newPassword)
	{
		if(newPassword ==null) {
			return false;
		}
		return newPassword.equals(password);
	}
	public boolean matchPassword(String newPassword) {
		if(newPassword==null) {
			return false;
		}
		return newPassword.equals(password);
	}
	public String getPassword() {
		return password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [UserId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
	public void update(User newUser) {
		this.password = newUser.password;
		this.name = newUser.name;
		this.email = newUser.email;
		
	}
	public Object getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	
}
