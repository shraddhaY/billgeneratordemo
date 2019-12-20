package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BillDao;
import com.example.dto.BillDetailsDto;
import com.example.dto.BillDto;
import com.example.dto.BillMasterDto;
import com.example.dto.ProductCategoryDetailsDto;
import com.example.dto.ProductDto;
import com.example.model.BillDetails;
import com.example.model.BillMaster;
import com.example.service.BillService;
import com.example.util.DateUtils;
import com.example.util.StringUtils;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillDao billDao;

	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	@Override
	public Optional<BillMasterDto> generateBill(BillDto billDto) {

		long currentTime = DateUtils.now();
		BillMaster billMaster = new BillMaster();
		List<BillDetails> billDetailsList = new ArrayList<>();
		BillDetails billDetails = null;
		BillMasterDto billMasterDto = new BillMasterDto();

		List<ProductDto> productDtoList = billDto.getProductList();
        System.out.println("productDtoList:----"+productDtoList);
		List<String> stringList = new ArrayList<>();

		for (ProductDto productDto : productDtoList) {
			stringList.add(productDto.getProductId());
			System.out.println("productDto"+ productDto);
			System.out.println("stringList----"+stringList);
		}

		Optional<List<ProductCategoryDetailsDto>> productCategoryDetailsListOptional = billDao.getProductCategoriesList(stringList);

		if (productCategoryDetailsListOptional.isPresent()) {
			List<ProductCategoryDetailsDto> productCategoryDetailsList = productCategoryDetailsListOptional.get();
			System.out.println("productCategoryDetailsList"+ productCategoryDetailsList);

			for (int i = 0; i < productCategoryDetailsList.size(); i++) {
				for (int j = 0; j < productDtoList.size(); j++) {
					if (productCategoryDetailsList.get(i).getId().equals(productDtoList.get(i).getProductId())) {
						System.out.println("check if is working or not");
						productCategoryDetailsList.get(i).setQuantity(productDtoList.get(i).getQuantity());
					}
				}
			}

			billMaster.setId(StringUtils.getUUID());
			billMaster.setInvoiceNumber(StringUtils.getBillInvoiceNumber());
			billMaster.setBillDate(currentTime);
			billMaster.setFirstName(billDto.getFirstName());
			billMaster.setLastName(billDto.getLastName());
			billMaster.setEmail(billDto.getEmail());
			billMaster.setMobileNumber(billDto.getMobileNumber());
			

			double totalPrice = 0;
			double totalTax = 0;
			double grandTotal = 0;

			for (ProductCategoryDetailsDto productCategoryDetailsDto : productCategoryDetailsList) {
				System.out.println("productCategoryDetailsDto"+productCategoryDetailsDto);

				billDetails = new BillDetails();
				billDetails.setId(StringUtils.getUUID());
				billDetails.setBillMasterId(billMaster.getId());
				billDetails.setProductMasterId(productCategoryDetailsDto.getId());
				billDetails.setQuantity(productCategoryDetailsDto.getQuantity());
				System.out.println("quantity:"+ productCategoryDetailsDto.getQuantity());
				billDetails.setPerProductPrice(productCategoryDetailsDto.getPrice());

				double perProductPrice = productCategoryDetailsDto.getPrice();
				double taxInPercentage = productCategoryDetailsDto.getTaxInPercent();
				double price = perProductPrice * productCategoryDetailsDto.getQuantity();
                 System.out.println("price"+ price);
				double taxAmount = (price / 100) * taxInPercentage;
				double totalAmount = price + taxAmount;

				billDetails.setPrice(price);
				billDetails.setTaxableAmount(taxAmount);
				billDetails.setTotalAmount(totalAmount);

				totalPrice = totalPrice + price;
				totalTax = totalTax + taxAmount;
				grandTotal = grandTotal + totalAmount;

				billDetailsList.add(billDetails);

			}
			
			billMaster.setTotalPrice(totalPrice);
			billMaster.setTotalTax(totalTax);
			billMaster.setGrandTotal(grandTotal);
			
			billDao.insertBillMasterDetails(billMaster);
			billDao.insertBillDetails(billDetailsList);
			
			billMasterDto = generateResponseForBilling(billMaster,billDetailsList,productCategoryDetailsList);
		}

		return Optional.ofNullable(billMasterDto);
	}

	private BillMasterDto generateResponseForBilling(BillMaster billMaster, List<BillDetails> billDetailsList, List<ProductCategoryDetailsDto> productCategoryDetailsList) {
		
		List<BillDetailsDto> billDetailsDtoList = new ArrayList<>();
		
		BillMasterDto billMasterDto = new BillMasterDto();
		billMasterDto.setId(billMaster.getId());
		billMasterDto.setInvoiceNumber(billMaster.getInvoiceNumber());
		billMasterDto.setFirstName(billMaster.getFirstName());
		billMasterDto.setLastName(billMaster.getLastName());
		billMasterDto.setEmail(billMaster.getEmail());
		billMasterDto.setMobileNumber(billMaster.getMobileNumber());
		
		long billDate = billMaster.getBillDate();
		String billDateInString = DateUtils.getFormattedDate(billDate);
		billMasterDto.setBillDate(billDateInString);
		billMasterDto.setTotalPrice(billMaster.getTotalPrice());
		billMasterDto.setTotalTax(billMaster.getTotalTax());
		billMasterDto.setGrandTotal(billMaster.getGrandTotal());
		
		BillDetailsDto billDetailsDto = null;

		for (BillDetails billDetails : billDetailsList) {
			
			billDetailsDto = new BillDetailsDto();
			
			billDetailsDto.setId(billDetails.getId());
			billDetailsDto.setBillMasterId(billDetails.getBillMasterId());
			billDetailsDto.setProductMasterId(billDetails.getProductMasterId());
			
			billDetailsDto.setQuantity(billDetails.getQuantity());
			billDetailsDto.setPerProductPrice(billDetails.getPerProductPrice());
			billDetailsDto.setPrice(billDetails.getPrice());
			billDetailsDto.setTaxableAmount(billDetails.getTaxableAmount());
			billDetailsDto.setTotalAmount(billDetails.getTotalAmount());
			
			for(int i=0;i<productCategoryDetailsList.size();i++) {
				if(billDetails.getProductMasterId().equals(productCategoryDetailsList.get(i).getId())) {
					billDetailsDto.setProductName(productCategoryDetailsList.get(i).getProductName());
					billDetailsDto.setCategoryId(productCategoryDetailsList.get(i).getCategoryId());
					billDetailsDto.setCategory(productCategoryDetailsList.get(i).getCategory());
					billDetailsDto.setTaxInPercent(productCategoryDetailsList.get(i).getTaxInPercent());
				}
			}
			
			billDetailsDtoList.add(billDetailsDto);
		}
		
		billMasterDto.setBillDetailsDtoList(billDetailsDtoList);
		
		return billMasterDto;
	}

}
