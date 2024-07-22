package com.itwill.springboot2.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	private final DepartmentService deptSvc;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list()");
		
		List<Department> list = deptSvc.read();
		
		model.addAttribute("departments", list);
	}
	
	@GetMapping("/details")
	public String departmentDetails(@RequestParam(name = "id") Integer id, Model model) {
		log.info("details(id={})", id);
		Department department = deptSvc.read(id);
		model.addAttribute("department", department);
		return "department/details";
	}

}
