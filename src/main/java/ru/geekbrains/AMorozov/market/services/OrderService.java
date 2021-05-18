package ru.geekbrains.AMorozov.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.AMorozov.market.dtos.OrderItemDto;
import ru.geekbrains.AMorozov.market.model.OrderItem;
import ru.geekbrains.AMorozov.market.repositories.OrderRepository;
import ru.geekbrains.AMorozov.market.model.Order;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItem orderItem;

    public void saveOrder(OrderItemDto orderItemDto){
        Order order = new Order();
        order.setProduct(orderItem.getProduct()); // TODO На мой взгляд - это решение не верное!!! (переделать)
        order.setQuantity(orderItemDto.getQuantity());
        order.setPricePerProduct(orderItemDto.getPricePerProduct());
        order.setPrice(orderItemDto.getPrice());
        orderRepository.save(order);
    }
}
