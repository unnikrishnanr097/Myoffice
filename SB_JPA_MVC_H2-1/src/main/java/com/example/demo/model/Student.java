package com.example.demo.model;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
@Table(name = "items")
public class Student {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	 @Column(name="name")
	private String name;
	 @Column(name="tech")
	private String tech;

	
	public String getTech() {
		return tech;
	}

	public void setTech(String tech) {
		this.tech = tech;
	}

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

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", tech=" + tech + "]";
	}

	
	

}
