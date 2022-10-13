package com.bozzco.orderservice.dto;

import com.bozzco.orderservice.model.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private List<OrderItemsDto> orderItemsList;
}
