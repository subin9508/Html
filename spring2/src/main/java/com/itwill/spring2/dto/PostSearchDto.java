package com.itwill.spring2.dto;

import java.time.LocalDateTime;

import com.itwill.spring2.repository.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder

public class PostSearchDto {    // new DTO();
								// dto.setCategory("");
								// dto.setKeyword("");
	private String category;
	private String keyword;
	

}