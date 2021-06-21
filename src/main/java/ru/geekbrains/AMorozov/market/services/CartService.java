package ru.geekbrains.AMorozov.market.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.geekbrains.AMorozov.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.AMorozov.market.models.Product;
import ru.geekbrains.AMorozov.market.utils.Cart;

@Service
@RequiredArgsConstructor
@Data
public class CartService{
    private final ProductService productService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final String PREF_CART_ID = "market_cart_";

    public void addToCart(String cartId, Long productId) {
        Cart cart = getCurrentCart(cartId);
        if (cart.addToCart(productId)) {
            save(cartId, cart);
            return;
        }
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with id " + productId + " is missed. (Add to cart)"));
        cart.addToCart(product);
        save(cartId, cart);
    }

    public void decrementProduct(String cartId, Long productId) {
        Cart cart = getCurrentCart(cartId);
        cart.decrementProduct(productId);
        save(cartId, cart);
    }

    public boolean isCartExists(String cartId) {
        return redisTemplate.hasKey(PREF_CART_ID + cartId);
    }

    public Cart getCurrentCart(String cartId) {
        if (!redisTemplate.hasKey(PREF_CART_ID + cartId)) {
            redisTemplate.opsForValue().set(PREF_CART_ID + cartId, new Cart());
        }
        Cart cart = (Cart) redisTemplate.opsForValue().get(PREF_CART_ID + cartId);
        return cart;
    }

    public void save(String cartId, Cart cart) {
        redisTemplate.opsForValue().set(PREF_CART_ID + cartId, cart);
    }

    public void clearCart(String cartId) {
        Cart cart = getCurrentCart(cartId);
        cart.clear();
        save(cartId, cart);
    }

    public void merge(String userCartId, String guestCartId) {
        Cart userCart = getCurrentCart(userCartId);
        Cart guestCart = getCurrentCart(guestCartId);
        userCart.merge(guestCart);
        save(userCartId, userCart);
        save(guestCartId, guestCart);
    }
}
