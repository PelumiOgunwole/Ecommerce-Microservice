package com.bozzco.inventoryservice;

import com.bozzco.inventoryservice.model.Inventory;
import com.bozzco.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository){
        return args -> {
            Inventory inventory = new Inventory();
            inventory.setSkuCode("Samsung Plasma TV");
            inventory.setQuantity(140);

            Inventory inventory1 = new Inventory();
            inventory1.setSkuCode("iphone 14");
            inventory1.setQuantity(00);

            inventoryRepository.save(inventory);
            inventoryRepository.save(inventory1);
        };
    }

}
