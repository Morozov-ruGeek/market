package ru.geekbrains.AMorozov.market.controllers;

import lombok.RequiredArgsConstructor;
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
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping(name = "/{id}")
    public Product findOne(@PathVariable Long id){
        return productService.findOneById(id).get();
    }

    @DeleteMapping(name = "/delete/{id}")
    public void deleteOneById(@PathVariable Long id){
        productService.deleteOneById(id);
    }

    @PostMapping(name = "/add")
    public Product createNewProduct(@RequestBody Product product){
        return productService.createNewProduct(product);
    }
}
