package ru.geekbrains.AMorozov.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.AMorozov.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.AMorozov.market.models.OrderItem;
import ru.geekbrains.AMorozov.market.models.Product;
import ru.geekbrains.AMorozov.market.utils.Cart;

@Service
@RequiredArgsConstructor
public class CartService{
    private final Cart cart;
    private final ProductService productService;

    public void addToCart(Long id) {
        for (OrderItem orderItem : cart.getItems()) {
            if (orderItem.getProduct().getId().equals(id)) {
                orderItem.incrementQuantity();
                cart.recalculate();
                return;
            }
        }
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + id + " (add to cart)"));
        cart.getItems().add(new OrderItem(product));
    }

    public void deleteProduct(Long id) {
        for (OrderItem orderItem : cart.getItems()) {
            if (orderItem.getProduct().getId().equals(id)) {
                orderItem.decrementQuantity();
                cart.recalculate();
                return;
            }
        }
    }

}
