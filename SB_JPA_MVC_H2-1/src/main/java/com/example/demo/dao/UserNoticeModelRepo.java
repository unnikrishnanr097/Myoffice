package com.example.demo.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.officemodel.UserNoticeModel;

public interface UserNoticeModelRepo extends JpaRepository<UserNoticeModel,Integer> {

	
	List<UserNoticeModel> findByUserid(int userid);


}
