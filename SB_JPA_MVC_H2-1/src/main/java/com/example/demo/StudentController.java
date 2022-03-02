package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.StudentRepo;
import com.example.demo.model.Student;

@RestController
public class StudentController {
	@Autowired
	StudentRepo repos;
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	
	@RequestMapping("/addstudent")
	public String add(Student st) {
		repos.save(st);
		return "home.jsp";
	}
	
	@RequestMapping("/getstudent")
	public ModelAndView get(@RequestParam int  id) {
		ModelAndView mv=new ModelAndView("Show.jsp");
		Student stdnt=repos.findById(id).orElse(new Student());
		mv.addObject(stdnt);
		
		System.out.println(repos.findByIdGreaterThan(103));
		System.out.println(repos.findByTechSorted("java"));

		return mv;
	}
	
	@RequestMapping("/updatestudent")
	public String update(@RequestParam int  id,Student st) {
		Student stdnt=repos.findById(id).orElse(new Student());
		repos.delete(stdnt);
		String ret=add(st);
		return ret;
	}
	
	@RequestMapping("/deletestudent")
	public String delete(@RequestParam int  id) {
		Student stdnt=repos.findById(id).orElse(new Student());
		repos.delete(stdnt);
		return "home.jsp";
	}
	
	//Webservices
	
	@RequestMapping(path="/students",produces= {"application/json"})
	
	public List<Student> getStudents() {
		
	
		return repos.findAll();
	}
	
	@RequestMapping("/students/{id}")
	
	public Optional<Student> getStudentat(@PathVariable("id") int  id) {
		
	
		return repos.findById(id);
	}
	
	@PostMapping(path="/admincreate",produces= {"application/json"})
	
	public Student postadmin(Student st) {
		
	repos.save(st);
		return st;
	}
	@RequestMapping(path="/admin",produces= {"application/json"})
	
		public List<Student> getadmin(Student st) {
		
		return repos.findAll();
	}
	
	

}
