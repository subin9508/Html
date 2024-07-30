package com.itwill.springboot5.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostCreateDto;
import com.itwill.springboot5.dto.PostListItemDto;
import com.itwill.springboot5.dto.PostUpdateDto;
import com.itwill.springboot5.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller @RequestMapping("/post")
public class PostController {
	
	private final PostService postSvc;
	
	@GetMapping("/list")
	public void list(@RequestParam(name = "p", defaultValue = "0") int pageNo, Model model) {
		log.info("list(pageNo={})", pageNo);
		
		// 서비스 계층의 메서드를 호출 -> 뷰에 포스트 목록 전달.
		Page<PostListItemDto> list = postSvc.read(pageNo, Sort.by("id").descending());
		model.addAttribute("page", list);
	}
	
	@GetMapping("/create")
	public void create() {
		log.info("create() GET");
	
	}
	
	@PostMapping("/create")
	public String create(PostCreateDto dto) {
		log.info("POST: create(dto={}", dto);
		
		// 서비스 계층의 메서드를 호출해서 작성한 포스트를 DB에 저장.
		postSvc.create(dto);
		
		return "redirect:/post/list";
		
	}
	
	@GetMapping({"/details", "/modify" }) // 하나의 메서드가 2개의 요청을 처리함.
	public void details(@RequestParam(name = "id") Long id, Model model) {
		log.info("details(id={})", id);
		
		Post entity = postSvc.readById(id);
		model.addAttribute("post", entity);
		
		//-> view의 이름은, 요청 주소가 "details"인 경우에는 details.html
		// 요청 주소가 "modify"인 경우에는 modify.html
	
	}
	
	@PostMapping("/update")
	public String update(PostUpdateDto dto) {
		log.info("update(dto={})", dto);
		postSvc.update(dto);
		
		return "redirect:/post/details?id=" + dto.getId();
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(name = "id") Long id) {
		log.info("delete(id={})", id);
		
		postSvc.delete(id);
		
		return "redirect:/post/list";
	}
	
}
