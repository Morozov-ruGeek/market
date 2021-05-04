package ru.geekbrains.AMorozov.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.AMorozov.market.model.Product;
import ru.geekbrains.AMorozov.market.services.CartService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public List<Product> showAllProductsInCart(){
        return cartService.showAllProductsInCart();
    }

    @PostMapping(name = "/cart/ping")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductInCart(@RequestBody Product product){
        cartService.addProductInCart(product);
    }

    @DeleteMapping(name = "/cart/clear")
    public void clearCart(){
        cartService.clearCart();
    }
}
