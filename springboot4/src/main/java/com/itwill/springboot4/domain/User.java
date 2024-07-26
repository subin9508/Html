package com.itwill.springboot4.domain;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "USERS")
public class User {
	
	@Id // 테이블에서 Primary Key라는 의미.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NaturalId // 테이블에서 Unique 제약조건을 의미.
	@Basic(optional = false) // true가 기본값. 테이블에서 not null 제약조건을 의미.
	private String username;
	
	@Basic(optional = false)
	private String password;
	
//	@Enumerated(EnumType.ORDINAL) // 쓰지 않아도 상관 없음, ORDINAL이 기본값. (0, 1, 2 형태로 저장)
	@Enumerated(EnumType.STRING) // enum에서 정의한 상수 이름을 문자열로 저장하는 컬럼.
	private Gender gender;
	
	
	@Column(length = 1000)
	private String memo;
	
	@Embedded // @Embeddable의 반대. @Embeddable로 선언된 객체를 포함할 때 사용. 생략 가능.
	private Address address;
}
