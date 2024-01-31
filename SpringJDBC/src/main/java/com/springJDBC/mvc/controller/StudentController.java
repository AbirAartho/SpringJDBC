package com.springJDBC.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springJDBC.mvc.model.Student;
import com.springJDBC.mvc.service.StudentService;


@Controller
public class StudentController {
	
	@Autowired
	public StudentService studentService;

	@GetMapping("/")
	public String getData(Model model) {
		
		List<Student> stuList = new ArrayList<>();
		stuList = studentService.findAll();
		model.addAttribute("list", stuList);

		return "jsp/index";
	}

	@GetMapping("/form")
	public String viewForm(Model model) {
		model.addAttribute("command", new Student());
		return "jsp/add_form";
	}

	@PostMapping("/save-student")
	public String saveStaff(@ModelAttribute Student student) {
		studentService.save(student);
		return "redirect:/";
	}

	@GetMapping("/student-delete/{id}")
	public String deleteStaff(@PathVariable("id") Long id) {
		studentService.deleteById(id);
		return "redirect:/";
	}

	
//  @GetMapping("/student-update/{id}")
//  public ModelAndView getStaff(@PathVariable("id") Long id) {
//      ModelAndView mav = new ModelAndView("jsp/add_form");
//      Student student = studentService.getDataByID(id);
//      mav.addObject("command", student);
//      return mav;
//  }
	
	
  @GetMapping("/student-update/{id}")
  public String getStaff(@PathVariable("id") Long id, Model model) {
      Student student = studentService.getDataByID(id);
	  model.addAttribute("command", student);
      return "jsp/add_form";
  }
  
}
