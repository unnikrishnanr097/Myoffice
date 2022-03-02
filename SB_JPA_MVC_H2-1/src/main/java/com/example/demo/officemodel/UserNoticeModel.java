package com.example.demo.officemodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usernoticemodel")
public class UserNoticeModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userid")
	private int userid;
	
	@Column(name="suggestionid")
	private int suggestionid;
	

	public int getSuggestionid() {
		return suggestionid;
	}

	public void setSuggestionid(int suggestionid) {
		this.suggestionid = suggestionid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	
	
	

}
