package ru.geekbrains.AMorozov.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.AMorozov.market.dtos.OrderItemDto;
import ru.geekbrains.AMorozov.market.services.OrderService;

@Controller
@RequiredArgsConstructor
@RequestMapping(name = "/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @PutMapping(name = "/create")
    public void createOrder(OrderItemDto orderItemDto){
        orderService.saveOrder(orderItemDto);
    }
}
