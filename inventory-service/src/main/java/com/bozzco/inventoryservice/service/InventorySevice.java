package com.bozzco.inventoryservice.service;

import com.bozzco.inventoryservice.dtos.InventoryResponse;


import java.util.List;

public interface InventorySevice {
    List<InventoryResponse> isInStock(List<String> skuCode);
}
