package com.neusoft.elmboot.po;

import lombok.Data;

@Data
public class OrderDetailet {

	private Integer odId;
	private Integer orderId;
	private Integer foodId;
	private Integer quantity;
	private Food food;

}
