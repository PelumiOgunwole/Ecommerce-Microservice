package com.bozzco.microserviceproduct.controller;


import com.bozzco.microserviceproduct.dto.ProductDTO;
import com.bozzco.microserviceproduct.dto.ProductResponse;
import com.bozzco.microserviceproduct.service.ProductService;
import com.bozzco.microserviceproduct.utils.APIResponse;
import com.bozzco.microserviceproduct.utils.Responder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<APIResponse> createProduct(@RequestBody ProductDTO productDTO){
        return Responder.okay(productService.createProduct(productDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllProducts(){
        return Responder.okay(productService.getAllProducts());
    }
}
