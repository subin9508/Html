package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.Post;
import com.itwill.lab05.service.PostService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "postCreateController", urlPatterns = { "/post/create" })
public class PostCreateController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostCreateController.class);
	
	private final PostService postService = PostService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doGet()");
		
		// 새 글 작성 폼(양식)을 작성하는 뷰(JSP)로 이동.
		req.getRequestDispatcher("/WEB-INF/views/post/create.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doPost()");
		
		// 요청(request)에 포함된 정보들(제목, 내용, 작성자)을 읽음.
		// req.getParameter(arg) 메서드의 아규먼트(arg)는 요청 파라미터의 이름으로 작성. name 속성의 값.
		String title = req.getParameter("title"); // create.jsp에서 input의 name을 메서드의 아규먼트로 작성.
		String content = req.getParameter("content"); // create.jsp에서 input의 name을 메서드의 아규먼트로 작성.
		String author = req.getParameter("author"); // create.jsp에서 input의 name을 메서드의 아규먼트로 작성.
		Post post = Post.builder().title(title).content(content).author(author).build();
		
		log.debug("post={}", post);
		
		// 서비스 객체의 메서드를 호출해서 DB 저장.
		postService.create(post);
		// 포스트 목록 페이지 이동.
		String url = req.getContextPath() + "/post/list";
		log.debug("redirect: " + url);
		resp.sendRedirect(url); // context root(path)를 함께 써주어야 함.
		// PRG(Post-Redirect-Get)
	}
	
}
