package com.itwill.springboot5.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot5.domain.Comment;
import com.itwill.springboot5.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CommentRepositoryTest {
	// CommentRepository의 CRUD 기능을 단위 테스트.
	
	@Autowired private CommentRepository commRepo;
	
	@Autowired private PostRepository postRepo;
	

	
	
	
	
//	@Test
	public void testFindAll() {
		List<Comment> list = commRepo.findAll();
		assertThat(list.size()).isEqualTo(0);
		
		list.forEach(System.out::println);
	}

}
