package com.ts.mvc.module.status;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PetStatusRepository extends JpaRepository<PetStatusSchedule, Long>{

	//PetStatusSchedule deleteBySchedule(PetStatusSchedule dto);
	                                                   // <entity, id값의 데이터 타입>

}
