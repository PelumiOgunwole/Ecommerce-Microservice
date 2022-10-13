package com.bozzco.microserviceproduct.mapper;

import com.bozzco.microserviceproduct.dto.ProductDTO;
import com.bozzco.microserviceproduct.dto.ProductResponse;
import com.bozzco.microserviceproduct.model.Product;

public class PayLoadMapper {

    public static Product mapRequestToProduct(ProductDTO request){
        Product product = new Product();
        if(request.getProductName()!=null){
            System.out.println(request.getProductName());
            product.setName(request.getProductName());
        }

        if(request.getDescription()!=null){
            product.setDescription(request.getDescription());
        }
        if(request.getPrice()!=null){
            product.setPrice(request.getPrice());
        }

        return product;
    }

    public static ProductResponse mapToProductResponse(Product product){
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(),product.getPrice());
    }
}
