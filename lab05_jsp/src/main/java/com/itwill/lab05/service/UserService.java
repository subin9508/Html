package com.itwill.lab05.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.User;
import com.itwill.lab05.repository.UserDao;

// 서비스(비즈니스) 계층 싱글턴 객체.
public enum UserService {
	INSTANCE;
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	private final UserDao userDao = UserDao.INSTANCE;
	
	// TODO: 회원 가입에 필요한 메서드. userDao.insert()를 호출하는 메서드.
	
	public int signUp(User user) {
		log.debug("signUp(user={})", user);
		
		int result = userDao.insert(user);
		log.debug("insert result = {}", result);
		
		return result;
	}
	
	public User signIn(String userId, String password) {
		log.debug("signIn(userId={}, password={}", userId, password);
		
		// DTO(Data Transfer Object): 데이터를 전달하는 객체.
		User dto = User.builder().userId(userId).password(password).build();
		User user = userDao.selectByUserIdAndPassword(dto);
		log.debug("로그인 결과 = {}", user);
		
		return user;
	}
	
}
