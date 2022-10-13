package com.bozzco.microserviceproduct.repository;

import com.bozzco.microserviceproduct.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
