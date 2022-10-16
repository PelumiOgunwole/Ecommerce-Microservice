package com.bozzco.inventoryservice.serviceimpl;

import com.bozzco.inventoryservice.dtos.InventoryResponse;
import com.bozzco.inventoryservice.model.Inventory;
import com.bozzco.inventoryservice.repository.InventoryRepository;
import com.bozzco.inventoryservice.service.InventorySevice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventorySevice {
    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory->
                    InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity()>0)
                        .build()
                )
                .toList();
    }
}
