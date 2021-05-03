package ru.geekbrains.AMorozov.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.AMorozov.market.model.Product;
import ru.geekbrains.AMorozov.market.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getOneProductById(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    @DeleteMapping
    public void deleteProduct(@RequestBody Product product) {
        productService.deleteById(product.getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createNewProduct(@RequestBody Product product){
        return productService.createNewProduct(product);
    }

    @PutMapping("/price")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Product changePriceById(@RequestBody Product product){
        return productService.changePriceById(product);
    }
}
