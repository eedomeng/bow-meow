package com.ts.mvc.module.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("blog")
public class BlogController {
	
	@GetMapping("")
	public String blog() {
		return "/html/blog";
	}

	@GetMapping("guestbook")
	public String guestbook() {
		return "/html/guestbook";
	}
	
	@GetMapping("diary")
	public String diary() {
		return "/html/diary";
	}
	
	@GetMapping("diary-modify")
	public String diaryMofdify() {
		return "/html/diary-modify";
	}
	
	@GetMapping("status")
	public String status() {
		return "/html/status";
	}

}
