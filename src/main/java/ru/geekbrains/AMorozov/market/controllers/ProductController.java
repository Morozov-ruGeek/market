package ru.geekbrains.AMorozov.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.AMorozov.market.model.Product;
import ru.geekbrains.AMorozov.market.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        List<Product> products = productService.findAll();
        return !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable (name = "id") Long id){
        Optional<Product> product = productService.findById(id);
        return  !product.isPresent()
                ? new ResponseEntity<>(product.get(), HttpStatus.ACCEPTED)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createNewProduct(@RequestBody Product product){
        return productService.createNewProduct(product);
    }

    @DeleteMapping
    public void deleteProduct(@RequestBody Product product) {
        productService.deleteById(product.getId());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Product changeProduct(@RequestBody Product product){
        return productService.changeProduct(product);
    }
}
