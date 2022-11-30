package com.socks.dto;

import lombok.Data;

@Data
public class orderDTO {

	private String BrDescription;
	private String DuDescription;
	private String Product_name;
	private String Product_code;
	private String Product_size;
	private String Product_color;
	private String Product_Qty;
	
	// 기간
	private String period;
	
	private String order_id;
	private String due_id;
	private String store_id;
	
}
