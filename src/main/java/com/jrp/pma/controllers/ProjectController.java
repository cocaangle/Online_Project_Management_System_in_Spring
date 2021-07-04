package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.TimeChartData;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projects", projects);
		return "projects/projectDisplay";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("project", new Project());
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		//handle saving to the database..
		proRepo.save(project);
		
		//prevent duplicate submissions
		return "redirect:/projects";
	}
	
	@GetMapping("/update")
	public String displayProjectUpdateForm(@RequestParam("id") long theId, Model model) {
		Project thePro =  proRepo.findByProjectId(theId);
		model.addAttribute("project", thePro);
		return "projects/new-project";
	}
	
	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") long theId, Model model) {
		Project thePro =  proRepo.findByProjectId(theId);
		proRepo.delete(thePro);
		return "redirect:/projects";
	}
	
	@GetMapping("/timelines")
	public String displayProjectTimelines(Model model) throws JsonProcessingException {
		
		List<TimeChartData>timelineData = proRepo.getTimeData();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimelineString = objectMapper.writeValueAsString(timelineData);
		
		System.out.println("---------- project timelines ----------");
		System.out.println(jsonTimelineString);
		
		model.addAttribute("projectTimeList", jsonTimelineString);
		return "projects/project-timelines";
	}
}
