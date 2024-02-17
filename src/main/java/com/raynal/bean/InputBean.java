package com.raynal.bean;

import java.util.List;

public class InputBean {
	private String customer_type;
	private List<String> dates;
	
	public String getCustomer_type() {
		return customer_type;
	}
	
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}
	
	public List<String> getDates() {
		return dates;
	}
	
	public void setDates(List<String> dates) {
		this.dates = dates;
	}
	
	@Override
	public String toString() {
		return "InputBean [customer_type=" + customer_type + ", dates=" + dates + "]";
	}
}
