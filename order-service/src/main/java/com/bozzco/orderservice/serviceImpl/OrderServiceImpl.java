package com.bozzco.orderservice.serviceImpl;

import com.bozzco.orderservice.dto.InventoryResponse;
import com.bozzco.orderservice.dto.OrderDTO;
import com.bozzco.orderservice.dto.OrderItemsDto;
import com.bozzco.orderservice.exception.OrderNotFoundException;
import com.bozzco.orderservice.exception.ProductNotFoundException;
import com.bozzco.orderservice.model.Order;
import com.bozzco.orderservice.model.OrderItems;
import com.bozzco.orderservice.repository.OrderRepository;
import com.bozzco.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
@Transactional

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private WebClient.Builder webClientBuilder;

    public OrderServiceImpl(OrderRepository orderRepository, WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;
        this.webClientBuilder = webClientBuilder;
    }



    public Order placeOrder(OrderDTO orderDTO){
        Order order  = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderItems> orderItemsList = orderDTO.getOrderItemsList().stream()
                .map(this::mapToDto).toList();
        order.setOrderItemsList(orderItemsList);

        List<String> skuCodes =order.getOrderItemsList().stream()
                .map(OrderItems::getSkuCode).toList();
        // Make a call to Inventory Service and place order if product in stock
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean allProductsInStock= Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
        if(allProductsInStock){
            return orderRepository.save(order);
        }
        else{
            throw new OrderNotFoundException("Order not possible");
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
