package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.officemodel.Leavecount;


public interface LeavecountRepo extends JpaRepository<Leavecount,Integer> {

	@Query("from Leavecount where userid=?1 ")
	Leavecount findByUserid1(String userid);
	
	

}
