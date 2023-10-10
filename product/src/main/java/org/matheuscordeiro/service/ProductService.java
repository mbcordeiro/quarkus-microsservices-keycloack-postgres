package org.matheuscordeiro.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.matheuscordeiro.dto.ProductDto;
import org.matheuscordeiro.entity.Product;
import org.matheuscordeiro.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductService {
    @Inject
    private ProductRepository productRepository;

    public List<ProductDto> getAllProducts() {
        final var productList = new ArrayList<ProductDto>();
        productRepository.findAll().stream().forEach(item -> {
            productList.add(mapProductEntityToDTO(item));
        });
        return productList;
    }

    public ProductDto getProductById(Long id) {
        return mapProductEntityToDTO(productRepository.findById(id));
    }

    public void createNewProduct(ProductDto product) {
        productRepository.persist(mapProductDTOToEntity(product));
    }

    public void changeProduct(Long id, ProductDto productDto) {
        final var product = productRepository.findById(id);
        product.setName(productDto.name());
        product.setCategory(productDto.category());
        product.setModel(productDto.model());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        productRepository.persist(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDto mapProductEntityToDTO(Product product) {
        return ProductDto.builder().name(product.getName()).description(product.getDescription())
                .category(product.getCategory()).model(product.getModel()).price(product.getPrice()).build();
    }

    private Product mapProductDTOToEntity(ProductDto productDto) {
        final var product = new Product();
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setCategory(productDto.category());
        product.setModel(productDto.model());
        product.setPrice(productDto.price());
        return product;
    }
}
