package com.socks.dto;

import java.util.Date;

import lombok.Data;

@Data
public class qtyDTO {

	// add
	private int order_id;
	
	// add
	private String product_code;
	
	private String product_name;
	private String product_size;
	private String product_color;
	// add
	private String product_qty;
	private int order_unit;
	
	// add
	private String store_id;
	
	// add
	private String due_id;
	
	private Date closing_date;
	
	// 배열
	private String[] data;
	private String name;
	
	
	//여러 배열
	private int[] orderId;
	private String[] storeId;
	private String[] dueId;
	private String[] productCode;
	private int[] productQty;
	
}
