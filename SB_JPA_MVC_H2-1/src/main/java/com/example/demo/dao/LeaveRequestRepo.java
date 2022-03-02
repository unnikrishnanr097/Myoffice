package com.example.demo.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.officemodel.LeaveRequest;

public interface LeaveRequestRepo extends JpaRepository<LeaveRequest,Integer>{
	
	ArrayList<LeaveRequest> findByStatus(int status);
	
	ArrayList<LeaveRequest> findByUserid(String userid);

//	@Query("from leaverequest1 where id=?1 ")
	LeaveRequest findById(int id);

	
}
 