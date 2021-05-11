package ru.geekbrains.AMorozov.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.AMorozov.market.dtos.CartDto;
import ru.geekbrains.AMorozov.market.model.Cart;
import ru.geekbrains.AMorozov.market.model.Product;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final Cart cart;

    public CartDto getProductsFromCart(){
        return new CartDto(cart);
    }

    public void addProductInCart(Long id){
        Optional <Product> product = productService.findById(id);
        product.ifPresent(cart::save);
    }

    public void deleteById(Long id){
        cart.deleteById(id);
    }

    public void clearCart(){
        cart.clearCart();
    }

}
