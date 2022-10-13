package com.bozzco.orderservice.serviceImpl;

import com.bozzco.orderservice.dto.OrderDTO;
import com.bozzco.orderservice.dto.OrderItemsDto;
import com.bozzco.orderservice.exception.ProductNotFoundException;
import com.bozzco.orderservice.model.Order;
import com.bozzco.orderservice.model.OrderItems;
import com.bozzco.orderservice.repository.OrderRepository;
import com.bozzco.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private WebClient webClient;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(OrderDTO orderDTO){
        Order order  = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderItems> orderItemsList = orderDTO.getOrderItemsList().stream()
                .map(this::mapToDto).collect(Collectors.toList());
        order.setOrderItemsList(orderItemsList);

        // Make a call to Inventory Service and place order if product in stock
        Boolean productAvailable = webClient.get()
                .uri("http://localhost:8082/api/inventory")
                .retrieve()
                .bodyToMono(Boolean.class) // specifies the return type
                .block(); // to make an asychronous call

        if(!Boolean.TRUE.equals(productAvailable)){
            throw new ProductNotFoundException;
        }

        else{
            return orderRepository.save(order);
        }
    }

    private OrderItems mapToDto(OrderItemsDto orderedItemDTO) {
        OrderItems orderItems = new OrderItems();
        orderItems.setPrice(orderedItemDTO.getPrice());

        orderItems.setQuantity(orderedItemDTO.getQuantity());
        orderItems.setSkuCode(orderedItemDTO.getSkuCode());
        return orderItems;
    }
}
