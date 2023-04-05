package com.ts.mvc.module.status;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.mvc.module.pet.Pet;
import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.UserRepository;

@Service
@Transactional(readOnly = true)
public class PetStatusService {
	
	private final PetStatusRepository petStatusRepository;
	private final UserRepository userRepository;
	
	// 의존성 주입 
	  public PetStatusService(PetStatusRepository petStatusRepository, UserRepository userRepository) {
	        this.petStatusRepository = petStatusRepository;
			this.userRepository = userRepository;
	    }
	  
	  public PetStatusSchedule createPetStatusSchedule(Long scheduleIdx, String title, LocalDateTime start, LocalDateTime end, Pet pet, User user) {
		
		  PetStatusSchedule petStatusSchedule = new PetStatusSchedule();
		  
		  petStatusSchedule.setTitle(title);
		  petStatusSchedule.setStart(start);
		  petStatusSchedule.setEnd(end);
		  petStatusSchedule.setScheduleIdx(scheduleIdx);
		  petStatusSchedule.setPet(pet);
		  petStatusSchedule.setUser(user);
		  
		  
		  return petStatusRepository.save(petStatusSchedule);
		  
	  }
	  
//	  @Transactional
//	  public PetStatusSchedule createSchedule(PetStatusScheduleDto dto) {
//		  
//		  User user = userRepository.findById(dto.getUserId()).get(); //userId를 찾고,
//		  PetStatusSchedule schedule = PetStatusSchedule.createPetStatusSchedule(dto, user);
//		  
//		  return petStatusRepository.saveAndFlush(schedule);
//	  }
	  
//	  public void deleteSchedule(PetStatusScheduleDto dto) {
//	       petStatusRepository.deleteBySchedule(dto);
//	  }
//	  
	 
	  // 풀캘린더 수정이 안되서 일단 보류
//	  public void updateSchedule(String userId,PetStatusScheduleDto dto) {
//		  User existingUser = userRepository.findByUserId(userId);
//		  
//	  }
	  
	  // 필요할까..? 아이디별 모든 스케줄 조회면 몰라도 다른 사람꺼도 보이면 좀.. 
//	  public List<PetStatusSchedule> getallSchedule(){
//		  return petStatusRepository.findAll(); // 모든 스케줄 정보 조회 
//	  }
	  
	  
//	//public String uploadSchedule(PetStatusScheduleDto ScheduleDto){
//	public List<Map<String, Object>> getEventList(){
//		Map<String, Object> event = new HashMap<String, Object>();
//		List<Map<String, Object>> eventList = new ArrayList<Map<String, Object>>();
//		
//		event.put("title", "test");
//		event.put("allday", "test");
//		event.put("Start", LocalDate.now());
//		event.put("End", LocalDate.now());
//		eventList.add(event);
//		event = new HashMap<String,Object>();
//		event.put("title","test2");
//		event.put("start", LocalDate.now());
//		event.put("End", LocalDate.now());
//		eventList.add(event);
//		
//		return eventList;
//	}
//	
	
}
