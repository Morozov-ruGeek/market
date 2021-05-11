package ru.geekbrains.AMorozov.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.AMorozov.market.dtos.ProductDto;
import ru.geekbrains.AMorozov.market.error_handling.InvalidDataException;
import ru.geekbrains.AMorozov.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.AMorozov.market.model.Category;
import ru.geekbrains.AMorozov.market.model.Product;
import ru.geekbrains.AMorozov.market.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<Product> findPage(int page, int pageSize){
        return productRepository.findAllBy(PageRequest.of(page, pageSize));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    @Transactional
    public ProductDto createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists product.categoryTitle =  " + productDto.getCategoryTitle() + "(for create)"));
        product.setCategory(category);
        product = productRepository.save(product);
        return new ProductDto(product);
    }

    public ProductDto updateProduct(ProductDto productDto){
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + productDto.getId() + "(for update)"));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists product.categoryTitle =  " + productDto.getCategoryTitle()));
        product.setCategory(category);
        return new ProductDto(product);
    }
}
