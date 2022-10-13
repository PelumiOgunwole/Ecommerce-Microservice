package com.bozzco.orderservice.controller;

import com.bozzco.orderservice.dto.OrderDTO;
import com.bozzco.orderservice.service.OrderService;
import com.bozzco.orderservice.utils.APIResponse;
import com.bozzco.orderservice.utils.Responder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<APIResponse> placeOrder(@RequestBody OrderDTO orderDTO){
        return Responder.okay(orderService.placeOrder(orderDTO));
    }

}
