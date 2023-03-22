package com.ts.mvc.module.guestbook;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ts.mvc.module.blog.dto.BlogDto;
import com.ts.mvc.module.guestbook.GuestBookService;
import com.ts.mvc.module.guestbook.dto.GuestBookDto;
import com.ts.mvc.module.user.UserPrincipal;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("guestbook")
@AllArgsConstructor
public class GuestBookController {

   private final GuestBookService guestBookService;

   
   
   
   @PostMapping("create")
   public String updateGuestBook(GuestBookDto guestBook) {

	   guestBook.setEmail(UserPrincipal.getUserPrincipal().getEmail());
	   System.out.println("입력받은 댓글은  :" + guestBook.getContent());
	   System.out.println("email : " + guestBook.getEmail());
	   System.out.println("등록일자 :" + guestBook.getRegdate());
	   System.out.println("gbIdx : "+ guestBook.getGbIdx());
	   guestBookService.createContent(guestBook);
	   return "/html/testguestbook";
   }
   
//   @PostMapping("create")
//   @ResponseBody
//   public GuestBookDto updateGuestBook(@RequestBody String comment,GuestBookDto guestBook) {
//	   guestBook.setContent(comment);
//
//	   guestBook.setEmail(UserPrincipal.getUserPrincipal().getEmail());
//	   System.out.println("입력받은 댓글은  :" + guestBook.getContent());
//	   System.out.println("gbIdx : "+ guestBook.getGbIdx());
//	   System.out.println("email : " + guestBook.getEmail());
//	   guestBookService.createContent(guestBook);
//	   return guestBook;
//   }

   // return 페이지 설정할 것.
   @GetMapping("")
   public String guestbook() {
      return "/html/testguestbook";
   }
   

   @PostMapping("remove")
   public String remove(Long bdIdx) {
	   guestBookService.removeBoard(bdIdx, UserPrincipal.getUserPrincipal().getPrincipal());
      return "redirect:/guestbook";
   }

   
   

}