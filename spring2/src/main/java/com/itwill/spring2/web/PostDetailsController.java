package com.itwill.spring2.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.repository.Post;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/post")

public class PostDetailsController {
	
	private final PostService postService;
	
	@GetMapping("/details")
	public void post(@RequestParam(name = "id") int id, Model model) {
		
		log.debug("id={}", id);
		
		Post post = postService.read(id);
		model.addAttribute("post", post);
		
	}
	

}
