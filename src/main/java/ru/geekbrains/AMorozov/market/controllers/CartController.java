package ru.geekbrains.AMorozov.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.AMorozov.market.dtos.CartDto;
import ru.geekbrains.AMorozov.market.services.CartService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController {
    private final CartService cartService;

    @GetMapping
    public CartDto getCart(){
        return cartService.getProductsFromCart();
    }

    @PostMapping(name = "/add/{id}")
    public void addProductInCart(@RequestParam Long id){
        cartService.addProductInCart(id);
        log.info("Added product id " + id);
    }

    @DeleteMapping("/clear")
    public void clearCart(){
        cartService.clearCart();
        log.info("Cart clear");
    }

    @DeleteMapping
    public void deleteProduct(@PathVariable Long id){
        cartService.deleteById(id);
        log.info("Product id " + id + "deleted");
    }
}
