package com.example.demo.officemodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.bytebuddy.utility.RandomString;

@Entity
@Table(name = "mailmodel")
public class Mailmodel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userid")
    private String userid;
	
	@Column(name="email")
    private String Email;
	
	@Column(name="verificationcode")
    private String verificationcode=getcode();
	
	@Column(name="enabled")
    private boolean enabled;

	
	
	public String getEmail() {
		return Email;
	}

	private String getcode() {
		String randomCode = RandomString.make(64);
		return randomCode;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getVerificationcode() {
		return verificationcode;
	}

	public void setVerificationcode(String verificationcode) {
		this.verificationcode = verificationcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}
