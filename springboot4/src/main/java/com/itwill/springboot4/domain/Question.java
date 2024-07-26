package com.itwill.springboot4.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "QUESTIONS")
public class Question {
	
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	//-> Oracle: create 명령어일 때, generated as identity 의미.
	//-> MySQL: autoincrement 의미.
	
	@Column(name = "QID") // 엔터티 필드 이름과 테이블 컬럼 이름이 다를 때.
	private Long id;
	
	@Basic(optional = false) // not null 제약 조건.
	private String title;
	
	@Basic(optional = false) @Column(length = 1000)
	private String content;
	

}
