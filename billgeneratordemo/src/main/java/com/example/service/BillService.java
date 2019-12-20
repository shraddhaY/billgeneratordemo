package com.example.service;

import java.util.Optional;

import com.example.dto.BillDto;
import com.example.dto.BillMasterDto;

public interface BillService {

	Optional<BillMasterDto> generateBill(BillDto billDto);

}

