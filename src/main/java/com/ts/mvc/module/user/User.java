package com.ts.mvc.module.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class User {
	
	@Id
	private String email;

	private String userName;
	private String password;	
	private String tell;
	
	@ColumnDefault("false")
	private Boolean isLeave;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	private String profilePhoto;


//	public static Member createMember(SignUpRequest dto) {
//		return Member.builder()
//				.userId(dto.getUserId())
//				.password(dto.getPassword())
//				.tell(dto.getTell())
//				.email(dto.getEmail())
//				.grade(dto.getGrade())
//				.build();
//	}
}
