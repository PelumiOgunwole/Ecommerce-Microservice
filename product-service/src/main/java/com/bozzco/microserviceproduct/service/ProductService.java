package com.bozzco.microserviceproduct.service;

import com.bozzco.microserviceproduct.dto.ProductDTO;
import com.bozzco.microserviceproduct.dto.ProductResponse;
import com.bozzco.microserviceproduct.model.Product;

import java.util.List;

public interface ProductService {
    public Product createProduct(ProductDTO productDTO);
    List<ProductResponse> getAllProducts();
//    Product getProductByName()
}
