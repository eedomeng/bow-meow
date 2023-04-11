package com.ts.mvc.module.status;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.mvc.module.status.dto.PetEventDto;
import com.ts.mvc.module.status.dto.PetStatusDto;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("status")
@AllArgsConstructor
public class PetStatusController {

	private PetEventService petEventService;
	private PetEventRepository petEventRepository;

	@GetMapping("")
	public String statusUpdate(HttpServletRequest request, Model model, PetStatusDto petStatus) {
		petStatus.setWater(request.getParameter("water"));
		petStatus.setFood(request.getParameter("food"));
		petStatus.setWeight(request.getParameter("weight"));
		petStatus.setTreat(request.getParameter("treat"));
		petStatus.setWalkDistance(request.getParameter("walkDistance"));
		  
		return "/html/status";
	}
	  
    //전체 이벤트 조회
//	@GetMapping("allEvents")
//	@ResponseBody // 객체면 전체 전송가능 
//	public String getAllPetEvents(){
//		System.out.println("확인용22");
//     	List<PetEventDto> petEvents = petEventService.getAllPetEvents();
//     	System.out.println("확인용");
//     	
//		return  "/status";
//		
//	}
	
	// 일정 등록
	@PostMapping("eventUpload")
	@ResponseBody 
	public ResponseEntity<Void> addPetEvent(@RequestBody String content, PetEventDto petEventDto) throws JsonMappingException, JsonProcessingException { // content에 petEventDto를 담는다. 
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(content);
		System.out.println(jsonNode);
		
		String title ="";
		String start="";
		String end="";
		
		for(int i=0; i<jsonNode.size(); i++) {
			
			title= jsonNode.get(i).get("title").asText();
			start= jsonNode.get(i).get("start").asText();
			end= jsonNode.get(i).get("end").asText();
			
			String[] strarr = start.split(".");
			String[] endarr = end.split(".");
			
			String startDay="2023-04-12T00:00:00";
			String endDay="2023-04-13T00:00:00";
			
			petEventDto.setStart(LocalDateTime.parse(startDay));
			petEventDto.setEnd(LocalDateTime.parse(endDay));
			petEventDto.setTitle(title);

		}
	
		petEventService.addPetEvent(petEventDto);
     	System.out.println("확인용");
     	
		return ResponseEntity.ok().build();
	}
	
//	// 일정 삭제
//	@ResponseBody 
//	@DeleteMapping("eventDelete/{eventIdx}")
//	public String deletePetEvent(@PathVariable Long eventIdx){
//		
//		petEventService.deletePetEvent(eventIdx);
//		return "/status/eventDelete";		
//	}
//	
	
}


