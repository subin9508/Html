package com.itwill.spring2.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.repository.Post;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // final 필드들을 초기화하는 생성자.
@Controller
@RequestMapping("/post") //-> PostController 클래스의 모든 컨트롤러 메서드의 매핑 주소는 "/post"로 시작.
public class PostController {
	
	private final PostService postService; // 생성자에 의한 의존성 주입.
	
	@GetMapping("/list")
	public void list(Model model) {
		log.debug("list()");
		
		// 서비스 컴포넌트의 메서드 호출. 포스트 목록을 읽어옴 -> 뷰에 전달.
		List<PostListDto> list = postService.read();
		model.addAttribute("posts", list);
		
		// 뷰: /WEB-INF/views/post/list.jsp
	}
	
	@GetMapping("/create")
	public void create() {
		log.debug("create() GET");
	}
	
	@PostMapping("/create")
	public String create(PostCreateDto dto) {
			log.debug("POST: create(dto={}", dto);
		
			// 서비스 컴포넌트의 메서드를 호출해 데이터베이스에 새 글을 저장.
			postService.create(dto);
			
			
			return "redirect:/post/list"; // 포스트 목록 페이지로 리다이렉트
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(name = "id") int id) {
		log.debug("delete(id={})", id);
		
		// 서비스 컴포넌트의 메서드를 호출해서 데이터베이스의 테이블에서 해당 아이디의 글을 삭제.
		postService.delete(id);
		
		// 포스트 목록 페이지로 리다이렉트.
		return "redirect:/post/list";
	}
	
	@PostMapping("/update")
	public String update(PostUpdateDto dto) {
		log.debug("POST: update(dto={}", dto);
		
		postService.update(dto);
		
		return "redirect:/post/details?id=" + dto.getId();
	}
	
	@GetMapping("/search")
	public void search(PostSearchDto dto, Model model) {
		log.debug("GET: search(dto={}", dto);
		
		
		List<PostSearchDto> list = postService.search(dto);
		model.addAttribute("posts", list);
		
		
		
		
	
	}
	
}
