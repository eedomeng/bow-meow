package com.ts.mvc.module.blog;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.mvc.module.blog.dto.request.WalkDto;
import com.ts.mvc.module.status.PetStatus;
import com.ts.mvc.module.status.PetStatusRepository;
import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.UserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class BlogService {
	
	private final PetStatusRepository petStatusRepository;
	private final UserRepository userRepository;
	
	@Transactional
	public void createStatus(String pageOwnerNickName ,WalkDto dto) {
		System.out.println("서비스레이어의 createStatus 실행.");
		System.out.println("pageOwnerNickName는 : "+ pageOwnerNickName);

		
//		User user = userRepository.findById(pageOwnerNickName).get();
		PetStatus petStatus = PetStatus.createPetStatus(pageOwnerNickName,dto);
		
		petStatusRepository.saveAndFlush(petStatus);
		
	}
	
	
	
}
