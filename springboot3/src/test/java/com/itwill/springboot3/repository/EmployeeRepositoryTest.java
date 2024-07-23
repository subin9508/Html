package com.itwill.springboot3.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class EmployeeRepositoryTest { // 테스트 클래스는 기본 생성자만 호출 가능. 생성자에 의한 의존성 주입 불가능.
	
	@Autowired // 의존성 주입
	private EmployeeRepository empRepo;
	
//	@Test
	public void testDependencyInjection() {
		// EmployeeRepository 객체를 의존성 주입을 받을 수 있는 지 테스트.
		assertThat(empRepo).isNotNull(); // null이 아니면 테스트 성공.
		log.info("empRepo={}", empRepo);
		
		/*
		 * Repository<T, ID>
		 * |__ CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>
		 * 		|__JpaRepository<T, ID>
		 * 			|__ EmployeeRepository<T, ID>
		 * 				|__ SimpleJpaRepository<T, ID>
		 */
		
	}
	
//	@Test
	public void testFindAll() {
		// employees 테이블 전체 검색 테스트:
		long count = empRepo.count();
		assertThat(count).isEqualTo(107L);
		
		List<Employee> list = empRepo.findAll();
		
		log.info("employees[0]={}", list.get(0));
	}
	
	@Transactional // fetch 타입에는 무조건 써주어야 테스트 오류가 나지 않는다.
	@Test
	public void testFindById() {
		// 1. EmployeeRepository.findById() 메서드 테스트
		// 2. Employees 테이블 - Jobs 테이블의 관계 테스트(JOB_ID - JOB_ID)
		// 3. Employees 테이블 - Employees 테이블 간의 관계 테스트(MANAGER_ID - EMPLOYEE_ID)
		
		// 테이블에 id(사번)가 존재하는 경우:
		Employee emp = empRepo.findById(101).orElseThrow();
		log.info("emp={}", emp);
		log.info("emp.job={}", emp.getJob());
		log.info("emp.manager={}", emp.getManager());
		log.info("emp.department={}", emp.getDepartment());
		log.info("emp.location={}", emp.getDepartment().getLocation().getCountry().getRegion());
		
	
	}
}
