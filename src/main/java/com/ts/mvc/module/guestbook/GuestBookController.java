package com.ts.mvc.module.guestbook;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ts.mvc.module.guestbook.dto.request.GuestBookDeleteRequest;
import com.ts.mvc.module.guestbook.dto.request.GuestBookRegistRequest;
import com.ts.mvc.module.guestbook.dto.request.GuestBookUpdateRequest;
import com.ts.mvc.module.guestbook.dto.response.GuestBookDetailResponse;
import com.ts.mvc.module.guestbook.dto.response.GuestBookListResponse;
import com.ts.mvc.module.user.UserPrincipal;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("guestbook")
public class GuestBookController {
	
	
	private final GuestBookService guestBookService;
	private final GuestBookRepository guestBookRepository;
	
	
	@GetMapping("")
	public String guestbook(GuestBookListResponse dto,Long gbIdx, Model model) {
		
		System.out.println("GetMapping('') 입니다.");
		
		
		List<GuestBook> guestbookList = guestBookRepository.findAll()
				.stream()
				.filter(entity -> entity != null)
				.collect(Collectors.toList());
		
		// 리스트 역정렬
		Collections.reverse(guestbookList);
		
		
		model.addAttribute("guestbookList",guestbookList);
				
		return "/html/guestbook";
	}
	


	@PostMapping("upload")
	@ResponseBody
	public String upload(@RequestBody String content, GuestBookRegistRequest dto, Model model) {
		
		System.out.println("GetMapping('upload') 입니다.");
		
		dto.setUserId(UserPrincipal.getUserPrincipal().getUserId());
		dto.setContent(content);
		model.addAttribute("content", content);
		guestBookService.createGuestBook(dto);
		System.out.println(dto.getContent());
		return "redirect:/guestbook";
	}
	
	@PutMapping("update")
	@ResponseBody
	public String update(@RequestBody GuestBookUpdateRequest dto, Model model) {
		
//		GuestBook guestbook = new GuestBook();
//		System.out.println("guestBook은 "+guestbook); 
		
//		dto.setUserId(UserPrincipal.getUserPrincipal().getUserId());
//		dto.setContent(content);
//		dto.setGbIdx(get);
		
		System.out.println("GetMapping('update') 입니다.");
		System.out.println("Controller가 받은 dto는"+ dto + "입니다.");
//		System.out.println("content : "+ content);
		
		
//		model.addAttribute("content", dto);  // content를 모델객체에 할당 == 타임리프 사용을 위함.
//		System.out.println("dto.getUserId는 :" + dto.getUserId());
//		System.out.println("dto.getContent는 :" + dto.getContent()); // 값 정상전달여부 확인
//		System.out.println("dto.getGbIdx는 :"+ dto.getGbIdx()); // 값 정상전달여부 확인
//		System.out.println("dto 는 :" + dto);
		guestBookService.updateGuestBook(dto); // 서비스 레이어에 updateGuestBook 메서드 선언
		return "redirect:/guestbook";
	}
	
	
	@DeleteMapping("delete")
	@ResponseBody
	public String delete(@RequestBody GuestBookDeleteRequest dto) {
		
		System.out.println("delete 매핑");
		System.out.println(dto);
		guestBookService.deleteGuestBook(dto);
		
		return "redirect:/guestbook";
	}
	
	@PostMapping("remove")
	public String remove(Long bdIdx) {
		guestBookService.removeGuestBook(bdIdx, UserPrincipal.getUserPrincipal().getPrincipal());
		return "redirect:/board/list";
	}
}
