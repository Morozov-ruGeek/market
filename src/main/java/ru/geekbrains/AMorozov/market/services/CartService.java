package ru.geekbrains.AMorozov.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.AMorozov.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.AMorozov.market.models.OrderItem;
import ru.geekbrains.AMorozov.market.models.Product;
import ru.geekbrains.AMorozov.market.utils.Cart;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService {
    private final Cart cart;
    private final ProductService productService;

    public void addToCart(Long id) {
        for (OrderItem orderItem : cart.getItems()) {
            if (orderItem.getProduct().getId().equals(id)) {
                orderItem.incrementQuantity();
                recalculate();
                return;
            }
        }

        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + id + " (add to cart)"));
        cart.getItems().add(new OrderItem(product));
        recalculate();
    }

    public void deleteProduct(Long id) {
        for (OrderItem orderItem : cart.getItems()) {
            if (orderItem.getProduct().getId().equals(id)) {
                orderItem.decrementQuantity();
                recalculate();
                return;
            }
        }
    }

    public void clear() {
        cart.getItems().clear();
        recalculate();
    }

    private void recalculate() {
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderItem oi : cart.getItems()) {
            sum = sum.add(oi.getPrice());
        }
        cart.setSum(sum);
    }
}
