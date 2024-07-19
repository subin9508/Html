package com.itwill.springboot2.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot2.domain.Department;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class DepartmentRepositoryTest {

	
	 @Autowired
	 private DepartmentRepository deptRepo;
	 
	 @Test
	 public void testDepartmentRepository() {
		 assertThat(deptRepo).isNotNull();
		 log.info("DepartmentRepository = {}", deptRepo);
		 
	 }
	 
	 @Test
	 public void testFindAll() {
		 List<Department> departments = deptRepo.findAll();
	        assertThat(departments).isNotEmpty();
	        for (Department dept : departments) {
	            log.info("Department: {}", dept);
	        }
	    } 
	 
	 
	 @Test
	 public void testFindById() {
		 Integer deptNo = 10;
		 Optional<Department> result = deptRepo.findById(deptNo);
		 
		 assertThat(result).isPresent();
		 Department department = result.get();
		 assertThat(department.getId()).isEqualTo(deptNo);
		 
		 log.info("Department found: {}", department);
	 }
	 
}
