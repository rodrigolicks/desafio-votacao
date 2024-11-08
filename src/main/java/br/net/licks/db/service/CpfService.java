package br.net.licks.db.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CpfService {
	
	public ResponseEntity<Map<String, String>> checkCpfForVoting() {
		
		Random random = new Random();
		Map<String, String> responseMap = new HashMap<String, String>();
		
		if (random.nextBoolean()) {
			responseMap.put("status", "ABLE_TO_VOTE");
			return ResponseEntity.status(HttpStatus.OK).body(responseMap);
		} else {
			responseMap.put("status", "UNABLE_TO_VOTE");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);			
		}
	}

}
