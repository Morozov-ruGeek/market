package ru.geekbrains.AMorozov.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.AMorozov.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.AMorozov.market.models.Order;
import ru.geekbrains.AMorozov.market.models.OrderItem;
import ru.geekbrains.AMorozov.market.models.User;
import ru.geekbrains.AMorozov.market.repositories.OrderRepository;
import ru.geekbrains.AMorozov.market.utils.Cart;


import java.io.Serializable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService{
    private final OrderRepository orderRepository;
    private final Cart cart;
    private final CartService cartService;
    private final UserService userService;

    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    public Order createOrderForCurrentUser(User user, String address, int phoneNumber) {
//        Long userId = userService.findByUsername(user.getUsername()).orElseThrow(() -> new ResourceNotFoundException("User not found by login " + user)).getId();
        Order order = new Order();
        order.setUser(user);
        order.setPrice(cart.getSum());
        order.setItems(cart.getItems());
        for (OrderItem oi : cart.getItems()) {
            oi.setOrder(order);
        }
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order = orderRepository.save(order);
        cart.clear();
        return order;
    }
}
