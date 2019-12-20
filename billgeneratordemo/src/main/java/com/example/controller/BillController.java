package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BillDto;
import com.example.dto.BillMasterDto;
import com.example.service.BillService;

@RestController
public class BillController {

	
	@Autowired
	private BillService billService;

	public void setBillService(BillService billService) {
		this.billService = billService;
	}
	
	@RequestMapping(value = "/api/bill", method = RequestMethod.POST)
	public ResponseEntity<?> generateBill(@RequestBody BillDto billDto){
		
		Optional<BillMasterDto> billMasterDto = billService.generateBill(billDto);
		
		if(billMasterDto.isPresent()) {
			return new ResponseEntity<>(billMasterDto.get(),  HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Something went wrong.",  HttpStatus.BAD_REQUEST);
	}
	
	
}
