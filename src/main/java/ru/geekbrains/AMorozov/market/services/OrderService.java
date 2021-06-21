package ru.geekbrains.AMorozov.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.AMorozov.market.dtos.OrderItemDto;
import ru.geekbrains.AMorozov.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.AMorozov.market.models.Order;
import ru.geekbrains.AMorozov.market.models.OrderItem;
import ru.geekbrains.AMorozov.market.models.User;
import ru.geekbrains.AMorozov.market.repositories.OrderRepository;
import ru.geekbrains.AMorozov.market.utils.Cart;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService{
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CartService cartService;

    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    public Order createOrderForCurrentUser(User user) {
        Order order = new Order();
        order.setUser(user);
        Cart cart = cartService.getCurrentCart(user.getUsername());
        order.setPrice(cart.getSum());
        order.setItems(new ArrayList<>());
        for (OrderItemDto o : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            order.getItems().add(orderItem);
            orderItem.setOrder(order);
            orderItem.setQuantity(o.getQuantity());
            orderItem.setPricePerProduct(o.getPricePerProduct());
            orderItem.setPrice(o.getPrice());
            orderItem.setProduct(productService.findById(o.getProductId()).get());
        }
        order = orderRepository.save(order);
        cart.clear();
        cartService.save(user.getUsername(), cart);
        return order;
    }
}
