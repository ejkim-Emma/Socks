package com.socks.dto;

import lombok.Data;

@Data
public class productDTO {

	// PK
	private String product_code;
	// 상품명
	private String product_name;
	// 사이즈
	private String product_size;
	// 색상
	private String product_color;
	// 가격
	private String unit_price;
	
}
