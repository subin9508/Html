package com.itwill.springboot5.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostSearchRequestDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PostQuerydslTest {
	
	@Autowired private PostRepository postRepo;
	
//	@Test
	public void testsearchById() {
		Post entity = postRepo.searchById(2L);
		log.info("entity = {}", entity);
	}
	
	
//	@Test
	public void test() {
		List<Post> result = null;
		result = postRepo.searchByTitle("DUMM");
		
//		result.forEach(System.out::println);
		for (int i = 0; i < 5; i++) {
			log.info("{}", result.get(i)); // result에서 index로 값 꺼내기.
		}
	}
	
//	@Test
	public void test2() {
		List<Post> result = null;
		result = postRepo.searchByContent("수정");
		
		result.forEach(System.out::println);
	}
	
	
//	@Test
	public void test3() {
		List<Post> result = null;
		result = postRepo.searchByTitleOrContent("te");
		result.forEach(System.out::println);
	}
	
	@Test
	public void test4() {
//		List<Post> result = null;
//		LocalDateTime from = LocalDateTime.of(2024, 7, 29, 0, 0);
//		LocalDateTime to = LocalDateTime.of(2024, 7, 30, 23, 59);
//		result = postRepo.searchByModifiedTime(from, to);
		
//		result = postRepo.searchByAuthorAndTitle("admin", "test");
		
//		PostSearchRequestDto dto = new PostSearchRequestDto();
//		dto.setCategory("tc");
//		dto.setKeyword("dum title");
//		result = postRepo.searchByCategory(dto);

//		result.forEach(System.out::println);
		
		String[] keywords = "te dum".split(" "); // { "dum", "title" };
//		result = postRepo.searchByKeywords(keywords);
		
		Pageable pageable = PageRequest.of(0, 5, Sort.by("id").descending());
		Page<Post> page = postRepo.searchByKeywords(keywords, pageable);
		page.forEach(System.out::println);
		

		
		
	}
}
