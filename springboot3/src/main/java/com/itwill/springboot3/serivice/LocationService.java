package com.itwill.springboot3.serivice;

import org.springframework.stereotype.Service;

import com.itwill.springboot3.domain.Location;
import com.itwill.springboot3.repository.LocationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class LocationService {
	
	private final LocationRepository locRepo;
	
	public Location read(Integer id) {
		log.info("read(id={})", id);
		
		return locRepo.findById(id).orElseThrow();
	}

}
