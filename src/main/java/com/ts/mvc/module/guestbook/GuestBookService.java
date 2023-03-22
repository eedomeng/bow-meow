package com.ts.mvc.module.guestbook;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.mvc.infra.code.ErrorCode;
import com.ts.mvc.infra.exception.AuthException;
import com.ts.mvc.infra.exception.HandlableException;
import com.ts.mvc.module.guestbook.dto.GuestBookDto;
import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.UserRepository;
import com.ts.mvc.module.user.dto.Principal;

import lombok.AllArgsConstructor;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class GuestBookService {

	private final GuestBookRepository guestBookRepository;
	private final UserRepository userRepository;
	
	@Transactional
	public void createContent(GuestBookDto dto) {

		User user = userRepository.findById(dto.getEmail()).get();
	      GuestBook guestbook = GuestBook.createBoard(dto, user);
	      
	      
	      guestBookRepository.saveAndFlush(guestbook);
	}

	
	@Transactional
	public void searchContent(GuestBookDto dto) {

		User user = userRepository.findById(dto.getEmail()).get();
	      GuestBook guestbook = GuestBook.createBoard(dto, user);
	      
	      
	      guestBookRepository.saveAndFlush(guestbook);
	}
	
	
	@Transactional
	   public void removeBoard(Long gbIdx, Principal principal) {
		GuestBook guestbook = guestBookRepository.findById(gbIdx)
	               .orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
	      
	      if(!guestbook.getUser().getEmail().equals(principal.getEmail())) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
	      
	           
	      guestBookRepository.delete(guestbook);
	      
	   }
	
	
}
