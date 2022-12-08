package com.socks.dto;

import java.util.Date;

import lombok.Data;

@Data
public class dutyfreeDTO {

	private String id;
	
	private String due_id;
	private String period;
	private Date closing_date;
	private String day;
	
	private String product_name;
	private String product_size;
	private String product_color;
	private String Product_Qty;
	
	private String store;
	private String product;
	
	private int order_unit;
	
}
