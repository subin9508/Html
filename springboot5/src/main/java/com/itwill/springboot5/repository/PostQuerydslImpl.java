package com.itwill.springboot5.repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.domain.QPost;
import com.itwill.springboot5.dto.PostSearchRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostQuerydslImpl extends QuerydslRepositorySupport 
	implements PostQuerydsl {
	
	public PostQuerydslImpl() {
		super(Post.class);
	}

	@Override
	public Post searchById(Long id) {
		log.info("searchById(id={})", id);
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post); // select p from Post p
		query.where(post.id.eq(id)); // query + where id = ?
		Post entity = query.fetchOne();
		
		return entity;
	}

	@Override
	public List<Post> searchByTitle(String keyword) {
		log.info("searchByTitle(keyword={})", keyword);
		
		QPost post = QPost.post; // static 필드를 가져옴.
		
		// Q포스트 객체를 아규먼트로 줌. 
		// select * from Post where id = ? order by desc
		JPQLQuery<Post> query = from(post).where(post.title.containsIgnoreCase(keyword)).orderBy(post.id.desc()); 
	
		List<Post> result = query.fetch();
		return result;
		
	}

	@Override
	public List<Post> searchByContent(String keyword) {
		log.info("searchByContent(keyword={})", keyword);
		
		QPost post = QPost.post;
		
		JPQLQuery<Post> query = from(post).where(post.content.containsIgnoreCase(keyword)).orderBy(post.id.desc());
		
		List<Post> result = query.fetch();
		
		return result;
	}

	@Override
	public List<Post> searchByTitleOrContent(String keyword) {
		log.info("searchByTitleOrContent(keyword={})", keyword);
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post).where(post.title.containsIgnoreCase(keyword).or(post.content.containsIgnoreCase(keyword))).orderBy(post.id.desc());
		List<Post> result = query.fetch();
		
		return result;
	}

	@Override
	public List<Post> searchByModifiedTime(LocalDateTime from, LocalDateTime to) {
	    log.info("searchByModifiedTime(from={}, to={})", from, to);

	    QPost post = QPost.post;
	    JPQLQuery<Post> query = from(post)
	        .where(post.modifiedTime.between(from, to))
	        .orderBy(post.modifiedTime.desc());
	    
	    List<Post> result = query.fetch();

	    return result;
	}

	@Override
	public List<Post> searchByAuthorAndTitle(String author, String title) {
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post).where(post.author.eq(author).and(post.title.containsIgnoreCase(title))).orderBy(post.id.desc());
		List<Post> result = query.fetch();
		
		return result;
	}

	@Override
	public List<Post> searchByCategory(PostSearchRequestDto dto) {
		log.info("searchByCategory(dto={})", dto);
		String category = dto.getCategory();
		String keyword = dto.getKeyword();
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post);
		
		// BooleanBuilder: where() 메서드의 아규먼트인 BooleanExpression 객체를 생성할 수 있는 객체
		BooleanBuilder builder = new BooleanBuilder();
		switch (category) {
		case "t":
			builder.and(post.title.containsIgnoreCase(keyword));
			break;
		case "c":
			builder.and(post.content.containsIgnoreCase(keyword));
			break;
		case "tc":
			builder.and(post.title.containsIgnoreCase(keyword)).or(post.content.containsIgnoreCase(keyword));
			break;
		case "a":
			builder.and(post.author.containsIgnoreCase(keyword));
			break;
		}
		
		query.where(builder).orderBy(post.id.desc()); // where 조건절에 따라서 다른 조건문들이 들어감.
		
		return query.fetch();
	}

	@Override
	public List<Post> searchByKeywords(String[] keywords) {
		log.info("searchByKeywords(keywords={})", Arrays.asList(keywords));
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post);
		BooleanBuilder builder = new BooleanBuilder();
		for(String k : keywords) { // 아규먼트 개수만큼 where 조건이 생기도록.
			builder.or(post.title.containsIgnoreCase(k)
					.or(post.content.containsIgnoreCase(k)));
		}
		query.where(builder).orderBy(post.id.desc());
		
		return query.fetch();
	}

	@Override
	public Page<Post> searchByKeywords(String[] keywords, Pageable pageable) {
		log.info("searchByKeywords(keyword={}, Pageable={})", 
				Arrays.asList(keywords), pageable);
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post);
		BooleanBuilder builder = new BooleanBuilder();
		for (String k : keywords) {
			builder.or(post.title.containsIgnoreCase(k).or(post.content.containsIgnoreCase(k)));
		}
		query.where(builder); // 전체 검색 쿼리.
		
		// Paging & Sorting 적용
		getQuerydsl().applyPagination(pageable, query); // pageable 객체를 적용할 수 있게끔 만듦.
		// 한 페이지에 표시할 데이터를 fetch. (select)
		List<Post> list = query.fetch(); // fetch()는 항상 페이지 타입을 리턴하지는 않기 때문에 List 객체에 우선 저장.
		log.info("list.size = {}", list.size());
		// 전체 레코드 개수를 fetch. (페이징 처리 하지 않은)
		long count = query.fetchCount();
		log.info("fetch count = {}", count);
		
		// Page<T> 객체를 생성. 
		Page<Post> page = new PageImpl<>(list, pageable, count);
		
		
		
		return page;
	}

}
