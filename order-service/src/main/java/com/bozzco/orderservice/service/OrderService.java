package com.bozzco.orderservice.service;

import com.bozzco.orderservice.dto.OrderDTO;
import com.bozzco.orderservice.model.Order;

public interface OrderService {
    Order placeOrder(OrderDTO orderDTO);
}
