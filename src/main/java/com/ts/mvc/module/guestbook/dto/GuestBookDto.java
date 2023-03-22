package com.ts.mvc.module.guestbook.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuestBookDto {

	private long gbIdx;
	private String content;
	private String email;
	private LocalDateTime regdate;
	
	
	public GuestBookDto(String content, long gbIdx, String email,LocalDateTime regdate) {
		this.content = content;
		this.gbIdx = gbIdx;
		this.email = email;
		this.regdate = regdate;
	}
}
