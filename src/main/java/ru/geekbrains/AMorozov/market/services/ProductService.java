package ru.geekbrains.AMorozov.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.AMorozov.market.dtos.ProductDto;
import ru.geekbrains.AMorozov.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.AMorozov.market.models.Category;
import ru.geekbrains.AMorozov.market.models.Product;
import ru.geekbrains.AMorozov.market.repositories.ProductRepository;
import ru.geekbrains.AMorozov.market.soap.soapproduct.SoapProduct;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService{
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<ProductDto> findAll(Specification<Product> spec, int page, int pageSize){
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDto::new);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public ProductDto createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category doesn't exists product.categoryTitle = " + productDto.getCategoryTitle() + " (Product creation)"));
        product.setCategory(category);
        productRepository.save(product);
        return new ProductDto(product);
    }

    @Transactional
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + productDto.getId() + " (for update)"));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category doesn't exists product.categoryTitle = " + productDto.getCategoryTitle() + " (Product creation)"));
        product.setCategory(category);
        return new ProductDto(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<SoapProduct> getAllProducts(){
        return productRepository.findAll().stream().map(functionProductToSoap).collect(Collectors.toList());
    }

    public SoapProduct getById(Long id) {
        return productRepository.findById(id).map(functionProductToSoap).get();
    }

    public static final Function<Product, SoapProduct>
            functionProductToSoap = product -> {
        SoapProduct sProduct = new SoapProduct();
        sProduct.setId(product.getId());
        sProduct.setTitle(product.getTitle());
        sProduct.setPrice(product.getPrice());
        sProduct.setSoapProductCategory(product.getCategory().getTitle());
        return sProduct;
    };
}
