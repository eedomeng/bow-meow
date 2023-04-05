package com.ts.mvc.module.status;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ts.mvc.module.pet.Pet;
import com.ts.mvc.module.status.dto.PetStatusDto;
import com.ts.mvc.module.status.dto.PetStatusScheduleDto;
import com.ts.mvc.module.user.User;

@Controller
@RequestMapping("status")
public class PetStatusController {

	private final  PetStatusService petStatusService;
	
	// 생성자를 만들어서 의존성 주입
	public PetStatusController(PetStatusService petStatusService) {
		this.petStatusService = petStatusService;
	}

	@GetMapping("")
	public String statusUpdate(HttpServletRequest request, Model model, PetStatusDto petStatus) {
		petStatus.setWater(request.getParameter("water"));
		petStatus.setFood(request.getParameter("food"));
		petStatus.setWeight(request.getParameter("weight"));
		petStatus.setTreat(request.getParameter("treat"));
		petStatus.setWalkDistance(request.getParameter("walkDistance"));
		  
		return "/html/status";
	}
	 
	@PostMapping("")
	public PetStatusSchedule createPetStatusSchedule(@RequestBody PetStatusScheduleDto petStatusScheduleDto) {
		
		String title = petStatusScheduleDto.getTitle();
		LocalDateTime start = petStatusScheduleDto.getStart();
		LocalDateTime end = petStatusScheduleDto.getEnd();
		Long scheduleIdx = petStatusScheduleDto.getScheduleIdx();
		Pet pet = petStatusScheduleDto.getPet();
		User user = petStatusScheduleDto.getUser();
		
		// 받아온 데이터를 이용하여 일정 생성
		PetStatusSchedule petStatusSchedule = petStatusService.createPetStatusSchedule(scheduleIdx,title,start,end,pet,user);
		                                                     
		return petStatusSchedule;
		
     	}

//	@PostMapping("")
//	@ResponseBody
//	public String addEvents(@RequestBody List<Map<String, Object>> param) throws Exception {
//
//		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",Locale.KOREA);
//		
//		for(Map<String, Object> list : param) {
//	     for(Map.Entry<String, Object> entry : list.entrySet()) {
//	    	 
//              String key = entry.getKey();
//              Object value = entry.getValue();
//              
//	     }
//	     
//	     // 받아올 값들 
//			String title = (String)list.get("title");
//			String start = (String) list.get("start");
//			String end = (String) list.get("end");
//			String allday = (String) list.get("allday");
//	
//			LocalDateTime startDate = LocalDateTime.parse(start, dateTimeFormatter);
//	        LocalDateTime endDate = LocalDateTime.parse(end, dateTimeFormatter);
//		
//		 // User user = UserService.findByUserId(user).get();
//		  
//		}
//		
//		return "/status";
//
//}
//	@PostMapping("")
//   
//    public  @ResponseBody List<Map<String,Object>> getEvent(){
//		return statusService.getEventList();
//	}
	
	
}


