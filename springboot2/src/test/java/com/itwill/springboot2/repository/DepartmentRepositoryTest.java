package com.itwill.springboot2.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class DepartmentRepositoryTest {

	
	 @Autowired
	 private DepartmentRepository deptRepo;
	 
	 @Test
	 @Order(1)
	 public void testDepartmentRepository() {
		 assertThat(deptRepo).isNotNull();
		 log.info("DepartmentRepository = {}", deptRepo);
		 
	 }
	 
	 @Transactional
	 @Test
	 @Order(2)
	 public void testFindAll() {
		 List<Department> departments = deptRepo.findAll();
	        assertThat(departments).isNotEmpty();
	        for (Department dept : departments) {
	            log.info("Department: {}", dept);
	        }
	    } 
	 
	 
	 @Transactional
	 @Test
	 @Order(3)
	 public void testFindById() {
		 Integer deptNo = 10;
		 Optional<Department> result = deptRepo.findById(deptNo);
		 
		 assertThat(result).isPresent();
		 Department department = result.get();
		 assertThat(department.getId()).isEqualTo(deptNo);
		 
		 log.info("Department found: {}", department);
		 
		 
		 // OneToMany 관계: 10번 부서의 모든 직원 정보 출력
		 List<Employee> employees = department.getEmployees();
		 employees.forEach(System.out::println);
		 
		 // 부서번호가 테이블에 없는 경우:
		 boolean isEmpty = deptRepo.findById(deptNo).isEmpty();
		 assertThat(isEmpty);
	 }
	 
}
