package com.itwill.springboot3.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot3.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class JpaQueryMethodTest {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Test
	public void test() {
		List<Employee> list = 
				// empRepo.findByDepartmentId(30);
				// empRepo.findByFirstName("Steven");
				// empRepo.findByFirstNameContaining("te");
				// empRepo.findByFirstNameLike("%te%");
				// empRepo.findByFirstNameContainingIgnoreCase("Te");
				// empRepo.findByFirstNameContainingIgnoreCaseOrderByFirstNameDesc("TE");
				// empRepo.findBySalaryGreaterThan(10_000.);
				// empRepo.findBySalaryLessThan(10_000.);
				// empRepo.findBySalaryBetween(10_000., 15_000.);
				// empRepo.findByHireDateLessThan(LocalDate.of(2003, 1, 1));
				// empRepo.findByHireDateGreaterThan(LocalDate.of(2007, 1, 1));
				empRepo.findByHireDateBetween(LocalDate.of(2007, 1, 1), LocalDate.of(2007, 12, 31));
				list.forEach(System.out::println);
	}
}
