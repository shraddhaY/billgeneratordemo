package com.example.dao;

import java.util.List;
import java.util.Optional;

import com.example.dto.ProductCategoryDetailsDto;
import com.example.model.BillDetails;
import com.example.model.BillMaster;

public interface BillDao {

	Optional<List<ProductCategoryDetailsDto>> getProductCategoriesList(List<String> stringList);

	int insertBillMasterDetails(BillMaster billMaster);

	int insertBillDetails(List<BillDetails> billDetailsList);

}
