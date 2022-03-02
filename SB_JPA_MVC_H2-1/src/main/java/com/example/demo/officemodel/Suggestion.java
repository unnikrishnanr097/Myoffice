package com.example.demo.officemodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "suggestion2")
public class Suggestion {
 

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	 @Column(name="content")
	private String content;
	 
	 @Column(name="date")
	private String date;
	 
	 @Column(name="upvote")
	private int upvote;
	 
	 @Column(name="downvote")
		private int downvote;
	 
	 
	 
	
	public int getDownvote() {
		return downvote;
	}
	public void setDownvote(int downvote) {
		this.downvote = downvote;
	}
	public int getUpvote() {
		return upvote;
	}
	public void setUpvote(int upvote) {
		this.upvote = upvote;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
