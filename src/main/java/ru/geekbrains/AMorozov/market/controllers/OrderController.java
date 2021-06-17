package ru.geekbrains.AMorozov.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.AMorozov.market.dtos.OrderDto;
import ru.geekbrains.AMorozov.market.models.User;
import ru.geekbrains.AMorozov.market.services.OrderService;
import ru.geekbrains.AMorozov.market.services.UserService;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController implements Serializable {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    public void createNewOrder(Principal principal,
                               @RequestBody OrderDto orderDto) {
        User user = userService.findByUsername(principal.getName()).get();
        orderService.createOrderForCurrentUser(user, orderDto.getAddress(), orderDto.getPhoneNumber());
    }

    @GetMapping
    @Transactional
    public List<OrderDto> getAllOrdersForCurrentUser(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        return orderService.findAllByUser(user).stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
