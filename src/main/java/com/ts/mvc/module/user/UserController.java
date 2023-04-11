package com.ts.mvc.module.user;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ts.mvc.infra.code.Code;
import com.ts.mvc.infra.code.ErrorCode;
import com.ts.mvc.infra.code.Role;
import com.ts.mvc.infra.exception.HandlableException;
import com.ts.mvc.module.guestbook.GuestBookService;
import com.ts.mvc.module.user.api.dto.UserUpdateDto;
import com.ts.mvc.module.user.dto.request.LoginRequest;
import com.ts.mvc.module.user.dto.request.SignUpRequest;
import com.ts.mvc.module.user.validator.SignUpValidator;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

	
	private final SignUpValidator signUpValidator;
	private final UserService userService;
	private final GuestBookService guestBookService;
	
	@InitBinder("signUpRequest")
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(signUpValidator);
	}
	
	@GetMapping("signup")
	public void signUp(Model model) {
		model.addAttribute("signUpRequest", new SignUpRequest());
	}
	
	@PostMapping("signup")
	public String authenticateEmail(@Valid SignUpRequest form,
								    Errors error,
								    Model model,
								    HttpSession session) {
		
		if(error.hasErrors()) {
			return "/user/signup";
		}
		
		session.setAttribute("signupForm", form);
		
		String authToken = UUID.randomUUID().toString();
		session.setAttribute("authToken", authToken);

		userService.authenticateEmail(form, authToken);
		
		return "redirect:/";
		
	}
	
	@GetMapping("signupimpl/{authToken}")
	public String signUpImpl(HttpSession session,
							 @PathVariable String authToken,
							 @SessionAttribute(name="authToken", required=false) String sessionToken,
							 @SessionAttribute(name="signupForm", required=false) SignUpRequest form,
							 Model model) {
		
		if(!authToken.equals(sessionToken)) {
			throw new HandlableException(ErrorCode.EXPRIATION_SIGNUP_TOKEN);
		}
		
		form.setGrade(Role.USER.desc());
		form.setProfileImageUrl(Role.PROFILEIMAGE.desc());
		userService.registNewMember(form);
		
		session.removeAttribute("authToken"); // 만료된 토큰 처리를 위해
		session.removeAttribute("signupForm"); // 메모리 아까워서
		
		return "redirect:/user/login";
	}
	
	@GetMapping("checkId")
	@ResponseBody
	public Map<String, Boolean> checkId(String userId) {
		return Map.of("exist", userService.existUser(userId));
	}

	@GetMapping("login")
	public void login(Model model) {
		model.addAttribute("loginRequest", new LoginRequest());
	}
	
	@GetMapping("modify")
	public String userModify(@AuthenticationPrincipal UserPrincipal userPrincipal) {
//		System.out.println("세션정보: " + userPrincipal.getUser());
		
		return "/user/user-modify";
	}
	
	@PostMapping("remove")
	public String remove(String userDelId, HttpServletRequest request, HttpServletResponse response) {
		userService.removeUser(userDelId);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		// 로그아웃 처리
		if(authentication != null){
	        new SecurityContextLogoutHandler().logout(request,response,authentication);
	    }
		
	    return "redirect:/";
		
	}
}
