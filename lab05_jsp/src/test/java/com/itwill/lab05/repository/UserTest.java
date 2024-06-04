package com.itwill.lab05.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTest {
	private static final Logger log = LoggerFactory.getLogger(UserTest.class);
	
	private final UserDao userDao = UserDao.INSTANCE;
	
	@Test
	public void testSignIn() { // 절대 아규먼트를 갖지 않는다.
		// userid와 password가 모두 일치하는 경우:
		User test = User.builder().userId("test").password("1234").build();
		User user = userDao.selectByUserIdAndPassword(test);
		Assertions.assertNotNull(user);
		
		// userid 또는 password가 둘 중 하나라도 일치하지 않는 경우:
		User test2 = User.builder().userId("test").password("test").build();
		User user2 = userDao.selectByUserIdAndPassword(test2);
		Assertions.assertNull(user2);
		
	}
}
