package com.bozzco.inventoryservice.serviceimpl;

import com.bozzco.inventoryservice.repository.InventoryRepository;
import com.bozzco.inventoryservice.service.InventorySevice;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventorySevice {
    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public boolean isInStock(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}
