package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;

@Controller
public class HomeController {
	
	@Value(value = "${version:dev}") 
	private String ver;
	@Autowired
	ProjectRepository proRepo;
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {
		
	   model.addAttribute("versionNumber", ver);
		
	   Map<String, Object> map = new HashMap<>();
	   
	   List<Project> projects = proRepo.findAll();
	   model.addAttribute("projectsList", projects);
	   
	   
	   List<ChartData> projectData = proRepo.getProjectStatus();
	   
	   //convert projectData into JSON structure for use in JavaScript
	   ObjectMapper objectMapper = new ObjectMapper();
	   String jsonString = objectMapper.writeValueAsString(projectData); //[[xxx.xx],[]]
	   model.addAttribute("projectStatusCnt", jsonString);
	   
	   List<EmployeeProject> employeesProjectsCnt = empRepo.employeeProjects();
	   model.addAttribute("employeeListProjectsCnt", employeesProjectsCnt);
	   
	   return "main/home";
    }
}
