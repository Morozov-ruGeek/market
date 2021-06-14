package ru.geekbrains.AMorozov.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.AMorozov.market.dtos.CartDto;
import ru.geekbrains.AMorozov.market.services.CartService;
import ru.geekbrains.AMorozov.market.utils.Cart;


import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController implements Serializable {
    private final CartService cartService;

    @GetMapping("/add/{productId}")
    public void addToCart(@PathVariable(name = "productId") Long id) {
        cartService.addToCart(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }

    @GetMapping
    public CartDto getCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/delete/{productId}")
    public void deleteProductFromCart(@PathVariable(name = "productId") Long id){
        cartService.deleteProductFromCart(id);
    }
}
