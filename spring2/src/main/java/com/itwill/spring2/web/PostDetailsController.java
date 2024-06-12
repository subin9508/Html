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
	
	@GetMapping({"/details", "modify"})
	//-> GET 방식의 "/post/details", "/post/modify" 2개의 요청을 처리하는 메서드.
	public void details(@RequestParam(name = "id") int id, Model model) {
					//	|__ 디스패쳐 서블릿이 req.getParameter("id")을 실행하고, 그 값을 아규먼트에 저장.	
		
		log.debug("details(id={})", id);
		
		// 서비스 컴포넌트의 메서드를 호출해서 해당 id의 포스트를 검색(select).
		Post post = postService.read(id);
		
		// 뷰에 데이터를 전달하기 위해서 model 객체에 post를 속성으로 추가.
		model.addAttribute("post", post);

		// 리턴 타입이 void이므로 뷰의 이름은
		// (1) 요청 주소가 /post/details인 경우 /WEB-INF/views/post/details.jsp
		// (2) 요청 주소가 /post/modify인 경우 /WEB-INF/views/post/modify.jsp

	}
	

}
