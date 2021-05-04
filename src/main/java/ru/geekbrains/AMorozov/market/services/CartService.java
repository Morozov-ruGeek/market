package ru.geekbrains.AMorozov.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.AMorozov.market.model.Product;
import ru.geekbrains.AMorozov.market.repositories.CartRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;


    public List<Product> showAllProductsInCart(){
        return cartRepository.findAll();
    }

    public void addProductInCart(Product product){
        cartRepository.save(product);
    }

    public void clearCart(){
        cartRepository.clearCart();
    }

}
