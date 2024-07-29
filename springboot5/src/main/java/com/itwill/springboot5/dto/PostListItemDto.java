package com.itwill.springboot5.dto;

import java.time.LocalDateTime;

import com.itwill.springboot5.domain.Post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// PostService ---> PostController (서비스에서 컨트롤러로 리턴할 때 쓸 클래스)
@Data
@NoArgsConstructor @AllArgsConstructor(access = AccessLevel.PRIVATE) @Builder
public class PostListItemDto {
	private Long id;
	private String title;
	private String content;
	private String author;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
	
	public static PostListItemDto fromEntity(Post entity) {
		return PostListItemDto.builder()
				.id(entity.getId())
				.title(entity.getTitle())
				.content(entity.getContent())
				.author(entity.getAuthor())
				.createdTime(entity.getCreatedTime())
				.modifiedTime(entity.getModifiedTime())
				.build();
	}
}
