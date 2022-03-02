package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.officemodel.Suggestion;



public interface SuggestionRepo extends JpaRepository<Suggestion,Integer>{

}
