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
	
	private int order_unit;
	
	private String[] selectCode;
	
	// 코드 배열 만들기
	private String[] productCode;
	private String[] productName;
	private String[] productSize;
	private String[] productColor;
	private int[] productPrice;
	private int[] productQty;

}
