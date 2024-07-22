package com.itwill.springboot2.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor //-> final 필드를 초기화하는 생성자. 생성자에 의한 의존성 주입 구현.
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	// 생성자에 의한 의존성 주입: (1) ReqiredArgsConstructor + (2) final field
	private final EmployeeService empSvc;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list()");
		
		// 서비스(비즈니스)계층의 메서드를 호출해서 데이터 베이스에 있는 직원 목록을 가져옴.
		List<Employee> list = empSvc.read();
		
		// 직원 목록을 뷰 템플릿에게 전달.
		model.addAttribute("employees", list);
	}
	
	
	@GetMapping("/details")
	public String employeeDetails(@RequestParam(name = "id") Integer id, Model model) {
		log.info("employeeDetails(id={})", id);
		Employee employee = empSvc.read(id);
		model.addAttribute("employee", employee);
		return "employee/details";
		
		
	}
	
	@GetMapping("/details/{id}")
	public String managerDetails(@PathVariable(name = "id") Integer id, Model model) {
		log.info("managerDetails(id={}", id);
		
		Employee employee = empSvc.read(id);
		if(employee.getManager() != null) {
			Employee manager = empSvc.read(employee.getManager().getId());
			model.addAttribute("employee", manager);
			return "redirect:/employee/details?id=" + employee.getManager().getId();
		} else {
			model.addAttribute("errorMessage", "이 직원의 매니저 정보가 존재하지 않습니다.");
			return "employee/details";
		}
		
	}

}
