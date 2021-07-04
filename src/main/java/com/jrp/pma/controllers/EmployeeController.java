package com.jrp.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public String displayEmployees(Model model) {
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employees", employees);
		return "employees/employeeDisplay";
	}
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createProject(Model model, @Valid Employee employee, Errors errors) {
		if(errors.hasErrors())
			return "employees/new-employee";
		//handle saving to the database..
		empRepo.save(employee);
		
		//prevent duplicate submissions
		return "redirect:/employees";
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {
		Employee theEmp =  empRepo.findByEmployeeId(theId);
		model.addAttribute("employee", theEmp);
		return "employees/new-employee";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long theId, Model model) {
		Employee theEmp =  empRepo.findByEmployeeId(theId);
		empRepo.delete(theEmp);
		return "redirect:/employees";
	}
	
	
}
