package com.itwill.springboot3.serivice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeService {
	
	private final EmployeeRepository empRepo;
	
	public List<Employee> read() {
		log.info("read()");
		
		return empRepo.findAll();
	}
	
	public Employee read(Integer id) {
		log.info("read=(id={})", id);
		return empRepo.findById(id).orElseThrow();
	}
	
	
}
