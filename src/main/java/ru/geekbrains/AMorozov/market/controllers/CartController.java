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
    private final Cart cart;

    @GetMapping("/add/{productId}")
    public void addToCart(HttpSession session, @PathVariable(name = "productId") Long id) {
        Cart cart = (Cart) session.getAttribute("user_cart");
        cartService.addToCart(id);
        session.setAttribute("user_cart", cart);
    }

    @GetMapping("/remove/{productId}")
    public void removeFromCart(@PathVariable(name = "productId") Long id){
//        todo задать вопрос на вебинаре правильную реализацию через HttpService or not
        cartService.deleteProduct(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clear();
    }

    @GetMapping
    public CartDto getCart() {
        return new CartDto(cart);
    }

    @GetMapping("/cart_sum")
    public BigDecimal getSum() {
        return cart.getSum();
    }
}
