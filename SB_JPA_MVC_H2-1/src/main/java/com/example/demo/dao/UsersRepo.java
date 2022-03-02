package com.example.demo.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.officemodel.Users;

public interface UsersRepo  extends JpaRepository<Users,Integer>{
	
	
	Users findByUserid(String userid);
	
	@Query("from Users where userid=?1 ")
	List<Users> findById1(String id);
	
	
//	@Query("from Users where userid=?1 and password=?2")
//	Users findById5(String userid,String password);
	
	@Query("from Users where id=?1 ")
	Users findById2(String id);
	
	@Query("from Users where email=?1 ")
	Users findByEmail1(String email);
}
