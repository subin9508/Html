package com.itwill.springboot3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot3.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> { // 기본 타입을 쓸 수 없고 클래스 타입만 쓸 수 있기 때문에 int 사용 불가. 
	

}
