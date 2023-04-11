package com.ts.mvc.module.welcome;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ts.mvc.infra.api.OpenAI;
import com.ts.mvc.module.pet.Pet;
import com.ts.mvc.module.pet.PetRepository;
import com.ts.mvc.module.status.PetStatus;
import com.ts.mvc.module.status.PetStatusRepository;
import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.dto.Principal;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class WelcomeController {
	
	private final PetRepository petRepository;
	
	@GetMapping("")
	public String index() {
		return "redirect:/index";
	}
	
	@GetMapping("/index")
	//user말고 다른 걸로
	public String welcome(User visitorId, Model model) {
		List<Pet> petStatus = petRepository.findAll();
//		List<Pet> filteredList = new ArrayList<>();
//		System.out.println(petStatus.size());
//	
//		for(int i=0; i<petStatus.size(); i++) {
//			if(String.valueOf(petStatus.get(i).getUser()).equals(visitorId.getUserId()) ) {
//				filteredList.add(petStatus.get(i));
//			} else {
//				
//			}
//			System.out.println(petStatus.get(i).getPetName());
//		
//		}
		
		
		model.addAttribute("petStatus", petStatus);
		return "/html/index";
	}
	
}
