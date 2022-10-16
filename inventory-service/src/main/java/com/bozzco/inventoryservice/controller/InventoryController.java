package com.bozzco.inventoryservice.controller;

import com.bozzco.inventoryservice.service.InventorySevice;
import com.bozzco.inventoryservice.utils.APIResponse;
import com.bozzco.inventoryservice.utils.Responder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventorySevice inventorySevice;

    public InventoryController(InventorySevice inventorySevice) {
        this.inventorySevice = inventorySevice;
    }

    @GetMapping
    public ResponseEntity<APIResponse> isInStock(@RequestParam List<String> skuCode){
        return Responder.okay(inventorySevice.isInStock(skuCode));
    }
}
