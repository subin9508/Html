package com.itwill.springboot5.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.itwill.springboot5.domain.Member;

import lombok.Data;

@Data
public class MemberSignUpDto {
	
	// html 파일, input의 name 속성명과 똑같이 설정.
	private String username; 
	private String password;
	private String email;
	
	public Member toEntity(PasswordEncoder encoder) { // 자신이 가지고 있는 필드의 값들을 이용하기 때문에 아규먼트 필요 없음.
		return Member.builder()
					.username(username).password(encoder.encode(password)).email(email).build();
	}
}
