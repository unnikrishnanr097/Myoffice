package com.example.demo.officemodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "leaverequest1")
public class LeaveRequest {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	 @Column(name="userid")
	private String userid;
	 
	 @Column(name="applieddate")
	private String applieddate;
	 
	 @Column(name="leavedate")
	private String leavedate;

	 @Column(name="leavecount")
	private int leavecount;
	 
	 @Column(name="reason")
	private String reason ;
	 
	 @Column(name="status")
	private int status;
	 
	 
	 
	 //getters and setters
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
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getApplieddate() {
		return applieddate;
	}
	public void setApplieddate(String applieddate) {
		this.applieddate = applieddate;
	}
	public String getLeavedate() {
		return leavedate;
	}
	public void setLeavedate(String leavedate) {
		this.leavedate = leavedate;
	}
	public int getLeavecount() {
		return leavecount;
	}
	public void setLeavecount(int leavecount) {
		this.leavecount = leavecount;
	}
	
	
	
	

}
