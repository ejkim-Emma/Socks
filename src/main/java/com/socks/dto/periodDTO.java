package com.socks.dto;

import lombok.Data;

@Data
public class periodDTO {

	private String Due_ID;
	private String Period;
	private int Year;
	private int Month;
	private String Sta_Date;
	private String Closing_Date;
	
	
	private String[] orderClose;
	private String[] orderStart;
	private String[] orderPeriod;
	private String[] orderDueId;
	private int[] orderYear;
	private int[] orderMonth;
	
	private String[] selectDueId;
	
}
