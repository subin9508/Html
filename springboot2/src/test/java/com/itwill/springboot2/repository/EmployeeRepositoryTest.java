package com.itwill.springboot2.repository;

// import static 구문: static으로 선언된 메서드, 필드 이름을 import 할 때 사용.
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import com.itwill.springboot2.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class EmployeeRepositoryTest {

	@Autowired // 의존성 주입(DI: dependency injection), 제어의 역전(IoC: Inversion of Control)
	private EmployeeRepository empRepo;
	
//	@Test
	public void test() {
		assertThat(empRepo).isNotNull(); // empRepo 객체가 null이 아니면 테스트 성공.
		log.info("{}", empRepo);
	}
	
	// select * from emp
//	@Test
	public void findAllTest() {
		List<Employee> list = empRepo.findAll();
		assertThat(list.size()).isEqualTo(14);
		
		for(Employee e : list) {
			System.out.println(e);
		}
	}
	
	
	@Transactional
//	@Test
	public void findByEmployeeNumberTest() {
		Integer empNo = 7788;
		Optional<Employee> result = empRepo.findById(empNo);
		
		assertThat(result).isPresent();
		Employee employee = result.get();
		// Employee employee = result.orElseGet(() -> null); 
		// assertThat(result).isNotNull(); : 사번이 존재하는 직원을 리턴해야 하기 때문에 null이 아니어야 함.
		assertThat(employee.getId()).isEqualTo(empNo);
		
		log.info("Employee found: {}", employee);
		log.info("scott={}", employee);
		log.info("dept={}", employee.getDepartment());
		
		
		// 사번이 테이블에 없는 경우:
		Integer empNo2 = 10000;
		Optional<Employee> result2 = empRepo.findById(empNo2);
		Employee none = result2.orElseGet(() -> null); // 사번이 존재하지 않는 직원이기 때문에 무조건 null을 리턴.
		assertThat(none).isNull();
	
	}
	
	@Transactional
	@Test
	public void findManagerTest() {
		// 사번이 7369인 직원 정보 검색:
		Employee emp = empRepo.findById(7369).orElseThrow(); // Optional을 쓰고 싶지 않을 때의 코드.
		// Optional<Employee> emp = empRepo.findById(7369); 
		assertThat(emp.getId()).isEqualTo(7369);
		log.info("emp={}", emp);
		
		Employee mgr = emp.getManager();
		assertThat(mgr.getId()).isEqualTo(7902); // 7369 직원의 매니저 사번은 7902이다.
		log.info("mgr={}", mgr);
		
		
	}
	

	
}
