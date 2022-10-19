package com.bozzco.inventoryservice;

import com.bozzco.inventoryservice.model.Inventory;
import com.bozzco.inventoryservice.repository.InventoryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
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
            inventory1.setQuantity(0);

            Inventory inventory3 = new Inventory();
            inventory3.setSkuCode("Moflix Diapers");
            inventory3.setQuantity(10);
            System.out.println(inventory1);
            inventoryRepository.save(inventory);
            inventoryRepository.save(inventory1);
            inventoryRepository.save(inventory3);
        };
    }


//    public  void run(String... args) throws Exception {
//        // read json and write to db
//        ObjectMapper mapper = new ObjectMapper();
//        TypeReference<List<Inventory>> typeReference = new TypeReference<>() {};
//        InputStream inputStream = TypeReference.class.getResourceAsStream("/ProductInventory.json");
//        try {
//            List<Inventory> schools = mapper.readValue(inputStream, typeReference);
//            List<Inventory> dBSchools = InventoryRepository.findAll();
//            for(Inventory sch: schools){
//                if(!dBSchools.contains(sch)){
//                    dBSchools.add(sch);
//                }
//            }
//            InventoryRepository.saveAll(dBSchools);
//        } catch (IOException e) {
//            throw new RuntimeException("Cannot save school");
//        }
//    }

}
