 package com.itwill.spring2.repository;

import java.util.List;

import com.itwill.spring2.dto.PostSearchDto;

public interface PostDao {

	// post-mapper.xml에서 id="selectOrderByIdDesc"인 SQL을 실행하는 메서드.
	List<Post> selectOrderByIdDesc();
	Post selectById(Integer id); // postDao에서 #{id}으로 값이 존재하기 때문에 파라미터 선언해야 함.
	int insertPost(Post post); // 파라미터 타입이 중요. Post 타입이어야 getter, setter를 호출할 수 있음.
	int updatePost(Post post);
	int deletePost(Integer id);
	List<Post> search(PostSearchDto dto);
}
