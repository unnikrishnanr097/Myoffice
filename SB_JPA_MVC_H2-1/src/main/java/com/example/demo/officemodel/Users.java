package com.example.demo.officemodel;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	 @Column(name="name")
	 private  String name;
	 
	 @Column(name="userid")
	 private String userid;
	 
	 @Column(name="address")
	 private String address;
	 
	 @Column(name="mobile")
	 private long mobile;
	 
	 
	 @Column(name="email")
	private String email;
	 
	 @Column(name="password")
	private String password;
	 
	 @Column(name="role")
	private String role="user";
	 
	 @Column(name="joiningdate")
	 private String joiningdate;
	 
	 @Column(name="qualification")
	 private String qualification;
	 
	 @Column(name="jobposition")
	 private String jobposition;
	 
	 @Column(name="salary")
	 private long salary;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getJoiningdate() {
		return joiningdate;
	}
	public void setJoiningdate(String joiningdate) {
		this.joiningdate = joiningdate;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getJobposition() {
		return jobposition;
	}
	public void setJobposition(String jobposition) {
		this.jobposition = jobposition;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", userid=" + userid + "]";
	}
	
	
	
		
		
		
		
	

}
