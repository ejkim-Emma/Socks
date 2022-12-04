package com.socks.dto;

import lombok.Data;

@Data
public class qtyDTO {

	private int order_id;
	private String product_name;
	private String product_size;
	private String product_color;
	private String product_qty;
	private int order_unit;
	
	private String store_id;
	private String due_id;
	
	
}
