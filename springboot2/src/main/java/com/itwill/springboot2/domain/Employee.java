package com.itwill.springboot2.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY) // 실제로 매니저 값이 필요할 때 다시 한번 select 함. (처음부터 join해서 모든 값을 불러오지 않음)
	@JoinColumn(name = "MGR")
	private Employee manager;
	
	private LocalDate hiredate;
	
	@Column(name = "SAL")
	private Double salary;
	
	@Column(name = "COMM")
	private Double commission;
	
	@ToString.Exclude // toString 메서드에서 출력 문자열에서 제외 (toString에 department는 나오지 않음)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPTNO") // EMP 테이블에서 DEPT 테이블과 join하는 컬럼 이름.
	private Department department; // 타입에 엔터티 클래스 이름을 사용.
}
