package com.ts.mvc.module.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.mvc.module.status.dto.PetEventDto;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class PetEventService {
	  
		@Autowired
		private PetEventRepository petEventRepository;
		

//	 // 삭제하기 
//       public void deletePetEvent(Long eventId) {
//    	   petEventRepository.deleteById(eventId);
//       }
//    
//       public List<PetEventDto> getAllPetEvents() {
//             List<PetEvent> petEvents = petEventRepository.findAll();
//             return petEvents.stream().map(this::toPetEventDto).collect(Collectors.toList());
//         }
       
//       // 전체 조회
//       private PetEventDto toPetEventDto(PetEvent petEvent) {
//    	    PetEventDto petEventDto = new PetEventDto();
//    	    
//    	    petEventDto.setEventIdx(petEvent.getEventIdx());
//    	    petEventDto.setTitle(petEvent.getTitle());
//    	    petEventDto.setStart(petEvent.getStart());
//    	    petEventDto.setEnd(petEvent.getEnd());
//
//    	    return petEventDto;
//    	}

		@Transactional
	public void addPetEvent(PetEventDto petEventDto) {
	
		PetEvent petEvent = PetEvent.createPetEvent(petEventDto);
		System.out.println("서비스레이어실행");
		
		petEventRepository.saveAndFlush(petEvent);
		
//		petEventDto.setTitle(title);
//		System.out.println(start);
//		System.out.println(end);
	}



	
	
}
