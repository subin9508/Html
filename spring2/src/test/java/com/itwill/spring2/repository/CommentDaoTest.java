package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/application-context.xml" }
)
public class CommentDaoTest {
	
	@Autowired // 스프링 컨테이너가 관리하는 빈을 생성/관리하는 빈을 주입받음. 
	private CommentDao commentDao;
	

//	@Test
	public void testSelect() {
		Assertions.assertNotNull(commentDao);
		
		List<Comment> list = commentDao.selectByPostId(22);
		for (Comment c :list) {
			log.debug(c.toString());
		}
	}
	
	
//	@Test
	public void testInsert() {
		Comment comment = Comment.builder().postId(63).username("guest").ctext("댓글 놀이").build();
		int result = commentDao.insert(comment);
		Assertions.assertEquals(1, result);
	}
	
	
//	@Test
	public void testUpdate() {
		Comment comment = Comment.builder().id(9).ctext("댓글 내용 업데이트!!!!").build();
		int result = commentDao.update(comment);
		Assertions.assertEquals(1, result);
				
	}
	
//	@Test
	public void testDeleteById() {
		int result = commentDao.deleteById(2);
		Assertions.assertEquals(1, result);
		
	}
	
//	@Test
	public void testDeleteByPostId() {
		int result = commentDao.deleteByPostId(63);
		Assertions.assertEquals(2, result);
	}
	
//	@Test
	public void testSelectCommentCount() {
		int result = commentDao.selectCommentCount(63);
		// 테이블에 댓글이 4개 있는 경우
		Assertions.assertEquals(4, result);
		
	}
	
	@Test
	public void testSelectById() {
		// 테이블에 댓글 아이디(PK)가 있는 경우
		Comment comment1 = commentDao.selectById(15);
		Assertions.assertNotNull(comment1);
		log.debug(comment1.toString());
		
		// 테이블에 댓글 아이디(PK)가 없는 경우
		Comment comment2 = commentDao.selectById(11);
		Assertions.assertNull(comment2);
		
	}
	
}



