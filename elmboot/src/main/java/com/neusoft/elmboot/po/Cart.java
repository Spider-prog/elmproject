package com.neusoft.elmboot.po;

import lombok.Data;

@Data
public class Cart {

	private Integer cartId;
	private Integer foodId;
	private Integer businessId;
	private String userId;
	private Integer quantity;
	private Food food;
	private Business business;

}
