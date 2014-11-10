package com.myprojects.tutorme.model;

import com.myprojects.tutorme.model.EncryptWithMD5;

public class User {
	private String emailId;
	private String fName;
	private String lName;
	private String phoneNumber;
	private String password;
	private String role;
	private String activated;
	//add Role field

	
	public String getEmailId(){
		return emailId;
	}	
	public void setEmailId(String email){
		this.emailId = email;
	}
	
	public String getFirstName(){
		return fName;
	}
	public void setFirstName(String fName){
		this.fName = fName;
	}
	
	public String getLastName(){
		return lName;
	}
	public void setLastName(String lName){
		this.lName = lName;
	}
	
	public String getFullName(){
		String fullName = this.fName + " " + this.lName;
		return fullName;
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	public String getEncryptedPassword(){
		return password;
	}
	public void setEncryptedPassword(String password){
		this.password = EncryptWithMD5.cryptWithMD5(password);
	}
	public void setPassword(String password){
		this.password = password;
	}

	//add getters and setters for role field
	
	public String getRole(){
		return role;
	}
	
	public void setRole(String role){
		this.role = role;
	}
	public String getActivated() {
		return activated;
	}
	public void setActivated(String activated) {
		this.activated = activated;
	}
}
