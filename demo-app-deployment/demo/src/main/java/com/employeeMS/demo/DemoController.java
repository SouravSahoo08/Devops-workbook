package com.employeeMS.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@Autowired
	private DemoRepo demoRepo;
	
	@PostMapping("save")
	public ResponseEntity<Object> save(@RequestBody DemoModel model){
		DemoModel saved = demoRepo.save(model);
		if(saved == null) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok().body(saved);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAll() {
		List<DemoModel> models = new ArrayList<>();
		models = demoRepo.findAll();
		return ResponseEntity.ok().body(models);		
	}
	
}
