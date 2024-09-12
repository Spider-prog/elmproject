package com.neusoft.elmboot.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.elmboot.mapper.CartMapper;
import com.neusoft.elmboot.mapper.OrderDetailetMapper;
import com.neusoft.elmboot.mapper.OrdersMapper;
import com.neusoft.elmboot.po.Cart;
import com.neusoft.elmboot.po.OrderDetailet;
import com.neusoft.elmboot.po.Orders;
import com.neusoft.elmboot.service.OrdersService;
import com.neusoft.elmboot.util.CommonUtil;

@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService{
	
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrderDetailetMapper orderDetailetMapper;

	@Override
	@Transactional
	public int createOrders(Orders orders) {
		//1、查询当前用户购物车中当前商家的所有食品
		Cart cart = new Cart();
		cart.setUserId(orders.getUserId());
		cart.setBusinessId(orders.getBusinessId());
		List<Cart> cartList = cartMapper.listCart(cart);
		
		//2、创建订单（返回生成的订单编号）
		orders.setOrderDate(CommonUtil.getCurrentDate());
		ordersMapper.saveOrders(orders);
		int orderId = orders.getOrderId();
		
		//3、批量添加订单明细
		List<OrderDetailet> list = new ArrayList<>();
		for(Cart c : cartList) {
			OrderDetailet od = new OrderDetailet();
			od.setOrderId(orderId);
			od.setFoodId(c.getFoodId());
			od.setQuantity(c.getQuantity());
			list.add(od);
		}
		orderDetailetMapper.saveOrderDetailetBatch(list);
		
		//4、从购物车表中删除相关食品信息
		cartMapper.removeCart(cart);
		
		return orderId;
	}
	
	@Override
	public Orders getOrdersById(Integer orderId) {
		return ordersMapper.getOrdersById(orderId);
	}
	
	@Override
	public List<Orders> listOrdersByUserId(String userId){
		return ordersMapper.listOrdersByUserId(userId);
	}

//	@Scheduled(cron = "0 * * * * ?")
//	public void processTimeoutOrder(){
//		log.info("处理支付超时订单：{}");
//
//		LocalDateTime time = LocalDateTime.now().plusMinutes(-15);
//
//		// select * from orders where status = 1 and order_time < 当前时间-15分钟
//		List<Orders> ordersList = ordersMapper.findByStatusAndOrdertimeLT(Orders.PENDING_PAYMENT, time);
//		if(ordersList != null && ordersList.size() > 0){
//			ordersList.forEach(order -> {
//				order.setStatus(Orders.CANCELLED);
//				order.setCancelReason("支付超时，自动取消");
//				order.setCancelTime(LocalDateTime.now());
//				ordersMapper.update(order);
//			});
//		}
//	}
}
