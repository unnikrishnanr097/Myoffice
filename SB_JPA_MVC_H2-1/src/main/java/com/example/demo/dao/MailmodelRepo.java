package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.officemodel.Mailmodel;

public interface MailmodelRepo  extends JpaRepository<Mailmodel,Integer>{

	Mailmodel findByUserid(String  userid);

}
