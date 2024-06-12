package com.itwill.spring2.dto;

import java.time.LocalDateTime;

import com.itwill.spring2.repository.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder

public class PostSearchDto {
	private String category;
	private String keyword;
	private Integer id;
	private String title;
	private String author;
	private LocalDateTime modifiedTime;
	
	public static PostSearchDto fromEntity(Post post) { // DB의 post 객체에서 필요한 것만 찾아 list를 만들어주는 메서드.
		return PostSearchDto.builder().id(post.getId()).title(post.getTitle()).author(post.getAuthor()).modifiedTime(post.getModifiedTime()).build();
	}

}