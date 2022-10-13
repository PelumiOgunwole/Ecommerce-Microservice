package com.bozzco.microserviceproduct.serviceimpl;

import com.bozzco.microserviceproduct.dto.ProductDTO;
import com.bozzco.microserviceproduct.dto.ProductResponse;
import com.bozzco.microserviceproduct.mapper.PayLoadMapper;
import com.bozzco.microserviceproduct.model.Product;
import com.bozzco.microserviceproduct.repository.ProductRepository;
import com.bozzco.microserviceproduct.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j

public class ProductImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = PayLoadMapper.mapRequestToProduct(productDTO);

        log.info("Product "+ product.getName()+ " is saved");
        return productRepository.save(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products= productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());

    }

    private ProductResponse mapToProductResponse(Product product) {
        return PayLoadMapper.mapToProductResponse(product);
    }
}
