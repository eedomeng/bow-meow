package com.ts.mvc.module.diary;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.mvc.infra.exception.CustomException;
import com.ts.mvc.module.diary.dto.DiaryUploadDto;
import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.UserPrincipal;
import com.ts.mvc.module.user.UserRepository;
import com.ts.mvc.module.user.api.dto.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiaryService {
	
	private final UserRepository userRepository;
	private final DiaryRepository diaryRepository;
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Transactional
	public void diaryImageUpload(DiaryUploadDto diaryUploadDto, UserPrincipal userPrincipal) {
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + diaryUploadDto.getFile().getOriginalFilename();
//		System.out.println("이미지 파일이름: " + imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		
		// 통신, I/O -> 예외가 발생할 수 있다.
		try {
			Files.write(imageFilePath, diaryUploadDto.getFile().getBytes());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// image 테이블에 저장
		Diary diary = diaryUploadDto.toEntity(imageFileName, userPrincipal.getUser());
		diaryRepository.save(diary);
		
//		System.out.println(diaryEntity);
	}
	
	@Transactional(readOnly = true)
	public UserProfileDto diaryForm(String pageUserId, String principalId) {
		UserProfileDto dto = new UserProfileDto();
		
		User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
			throw new CustomException("해당 다이어리는 없는 페이지입니다.");
		});
		
		dto.setUser(userEntity);
		dto.setPageOwnerState(pageUserId.equals(principalId)); // 같으면 true
		dto.setImageCount(userEntity.getImages().size());
		
		return dto;
	}


}