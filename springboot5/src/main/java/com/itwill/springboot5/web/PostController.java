package com.itwill.springboot5.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostCreateDto;
import com.itwill.springboot5.dto.PostListItemDto;
import com.itwill.springboot5.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller @RequestMapping("/post")
public class PostController {
	
	private final PostService postSvc;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list()");
		// TODO: 서비스 계층의 메서드를 호출 -> 뷰에 포스트 목록 전달.
		List<PostListItemDto> list = postSvc.read();
		model.addAttribute("posts", list);
	}
	
	@GetMapping("/create")
	public void create() {
		log.info("create() GET");
	
	}
	
	@PostMapping("/create")
	public String create(PostCreateDto dto) {
		log.info("POST: create(dto={}", dto);
		
		postSvc.create(dto);
		
		return "redirect:/post/list";
		
	}
	
	@GetMapping("/details/{id}")
	public String details(@PathVariable Long id, Model model) {
		log.info("details(id={})", id);
		
		PostListItemDto dto = postSvc.read(id);
		model.addAttribute("post", dto);
		
		return "post/details";
	}
	
	
}
