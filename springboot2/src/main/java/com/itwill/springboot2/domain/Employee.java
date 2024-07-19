package com.itwill.springboot2.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// ORM(Object Relation Mapping) -> JPA(Java Persistence API) -> Hibernate
@NoArgsConstructor @Getter @ToString @EqualsAndHashCode
@Entity // 데이터베이스 테이블과 매핑하는 자바 객체
@Table(name = "EMP") // 클래스 이름과 실제 테이블 이름이 다를 때 작성.
public class Employee {
	@Id // Id 필드, Primary Key
	@Column(name = "EMPNO") // 필드 이름과 실제 컬럼 이름이 다를 때 작성. (같을 때는 작성할 필요 없음)
	private Integer id;
	
	private String ename;
	private String job;
	
	@Column(name = "MGR")
	private Integer manager;
	
	private LocalDate hiredate;
	
	@Column(name = "SAL")
	private Double salary;
	
	@Column(name = "COMM")
	private Double commission;
	
	private Integer deptno;
}
