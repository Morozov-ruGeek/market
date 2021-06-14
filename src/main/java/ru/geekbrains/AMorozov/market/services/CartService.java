package ru.geekbrains.AMorozov.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.AMorozov.market.dtos.CartDto;
import ru.geekbrains.AMorozov.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.AMorozov.market.models.OrderItem;
import ru.geekbrains.AMorozov.market.models.Product;
import ru.geekbrains.AMorozov.market.utils.Cart;

@Service
@RequiredArgsConstructor
public class CartService{
    private final Cart cart;
    private final ProductService productService;

    public void addToCart(Long productId){
        if(cart.addToCart(productId)){
            return;
        }

        Product product = productService.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product doesn't exists id: " + productId + " (add to cart)"));
        cart.addToCart(product);
    }

    public CartDto getCurrentCart(){
        return new CartDto(cart);
    }

    public void clearCart(){
        cart.clear();
    }

    public void deleteProductFromCart(Long id) {
        cart.deleteProduct(id);
    }
}
