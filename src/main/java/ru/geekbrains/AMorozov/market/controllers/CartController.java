package ru.geekbrains.AMorozov.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.AMorozov.market.dtos.StringResponse;
import ru.geekbrains.AMorozov.market.services.CartService;
import ru.geekbrains.AMorozov.market.utils.Cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController implements Serializable {
    private final CartService cartService;

    @GetMapping("/generate")
    public StringResponse getNewCartId() {
        String uuid = null;
        do {
            uuid = UUID.randomUUID().toString();
        } while (cartService.isCartExists(uuid));
        return new StringResponse(uuid);
    }

    @GetMapping("/merge")
    public void mergeCarts(Principal principal, @RequestParam String cartId) {
        cartService.merge(principal.getName(), cartId);
    }

    @GetMapping("/add")
    public void addToCart(Principal principal, @RequestParam(name = "prodId") Long id, @RequestParam String cartName) {
        if (principal != null) {
            cartName = principal.getName();
        }
        cartService.addToCart(cartName, id);
    }

    @GetMapping("/dec")
    public void decrementProduct(Principal principal, @RequestParam(name = "prodId") Long id, @RequestParam String cartName) {
        if (principal != null) {
            cartName = principal.getName();
        }
        cartService.decrementProduct(cartName, id);
    }

    @GetMapping("/clear")
    public void clearCart(Principal principal, @RequestParam String cartName) {
        if (principal != null) {
            cartName = principal.getName();
        }
        cartService.clearCart(cartName);
    }

    @GetMapping
    public Cart getCart(Principal principal, @RequestParam String cartName) {
        if (principal != null) {
            cartName = principal.getName();
        }
        return cartService.getCurrentCart(cartName);
    }
}
