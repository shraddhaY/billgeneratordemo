package com.example.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.example.dao.BillDao;
import com.example.dto.ProductCategoryDetailsDto;
import com.example.model.BillDetails;
import com.example.model.BillMaster;

@Repository
public class BillDaoImpl implements BillDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Optional<List<ProductCategoryDetailsDto>> getProductCategoriesList(List<String> stringList) {

		//@formatter:off
			String sql = "SELECT PM.id,PM.product_name,PM.price,C.id as category_id,C.category,C.tax_in_percent FROM product_master PM INNER JOIN categories C ON PM.category_id = C.id WHERE PM.id IN(";
			 
			List<Object> params = new ArrayList<>();		

			for (int i = 0; i < stringList.size(); i++) {
				sql += (i == stringList.size() - 1) ? "?) " : "?,";

				params.add(stringList.get(i));
			}

		// @formatter:on		     
		

		List<ProductCategoryDetailsDto> productCategoryDetailsDtoList = jdbcTemplate.query(sql, new ResultSetExtractor<List<ProductCategoryDetailsDto>>() {
			@Override
			public List<ProductCategoryDetailsDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<ProductCategoryDetailsDto> list = new ArrayList<ProductCategoryDetailsDto>();
				while (rs.next()) {

					ProductCategoryDetailsDto productCategoryDetails = new ProductCategoryDetailsDto();

					productCategoryDetails.setId(rs.getString("id"));
					productCategoryDetails.setProductName(rs.getString("product_name"));
					productCategoryDetails.setPrice(rs.getDouble("price"));
					productCategoryDetails.setCategoryId(rs.getString("category_id"));
					productCategoryDetails.setCategory(rs.getString("category"));
					productCategoryDetails.setTaxInPercent(rs.getDouble("tax_in_percent"));
				
					list.add(productCategoryDetails);
				}

				return list;
			}
		},params.toArray());
		if (productCategoryDetailsDtoList.isEmpty()) {
			return Optional.empty();
		}

		return Optional.ofNullable(productCategoryDetailsDtoList);
	}

	@Override
	public int insertBillMasterDetails(BillMaster billMaster) {
		
		
		// @formatter:off
		       String sql = "INSERT INTO"
		       			+ " bill_master("
		       			+ "		id, invoice_number, first_name, last_name, email, "
		       			+ "		mobile_number, total_price, total_tax, grand_total, bill_date"
		       			+ ")"
		       			+ "VALUES"
		       			+ "		( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		// @formatter:on
				
				return jdbcTemplate.update(sql, new Object[] {
						billMaster.getId(),
						billMaster.getInvoiceNumber(),
						billMaster.getFirstName(),
						billMaster.getLastName(),
						billMaster.getEmail(),
						billMaster.getMobileNumber(),
						billMaster.getTotalPrice(),
						billMaster.getTotalTax(),
						billMaster.getGrandTotal(),
						billMaster.getBillDate()
						
					});
	}

	@Override
	public int insertBillDetails(List<BillDetails> billDetailsList) {
		
		// @formatter:off
		       String sql = "INSERT INTO"
		       			+ "	 bill_details("
		       			+ "			id, bill_master_id, product_master_id, quantity, per_product_price,"
		       			+ "			price, taxable_amount, total_amount)"
		       			+ "VALUES"
		       			+ "		     ( ?, ?, ?, ?, ?, ?, ?, ?)";
		       
			
		// @formatter:on
		       
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {

					BillDetails billDetails = billDetailsList.get(i);

					ps.setString(1, billDetails.getId());
					ps.setString(2, billDetails.getBillMasterId());
					ps.setString(3, billDetails.getProductMasterId());
					ps.setDouble(4, billDetails.getQuantity());
					ps.setDouble(5, billDetails.getPerProductPrice());
					ps.setDouble(6, billDetails.getPrice());
					ps.setDouble(7, billDetails.getTaxableAmount());
					ps.setDouble(8, billDetails.getTotalAmount());

				}

				@Override
				public int getBatchSize() {
					return billDetailsList.size();
				}
			});

			return billDetailsList.size();
	}

}
