package com.itwill.spring2.dto;

import java.time.LocalDateTime;

import com.itwill.spring2.repository.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO(Data Transfer Object)
// 뷰 <--> 컨트롤러, 컨트롤러 <--> 서비스 사이에서 데이터를 주고 받을 때 사용하는 객체.
@Data // @Getter + @Setter + @ToString + @EqualsAndHashCode + @RequiredArgsConstructor(final 필드가 없으면 기본 생성자와 같음)
@NoArgsConstructor @AllArgsConstructor @Builder
public class PostListDto {
	
	private Integer id;
	private String title;
	private String author;
	private LocalDateTime modifiedTime;
	
	public static PostListDto fromEntity(Post post) { // DB의 post 객체에서 필요한 것만 찾아 list를 만들어주는 메서드.
		return PostListDto.builder().id(post.getId()).title(post.getTitle()).author(post.getAuthor()).modifiedTime(post.getModifiedTime()).build();
	}
}
