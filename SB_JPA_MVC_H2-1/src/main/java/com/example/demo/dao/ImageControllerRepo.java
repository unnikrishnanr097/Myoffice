package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.officemodel.Image;

public interface ImageControllerRepo extends JpaRepository<Image,Integer>{
	
	
}
