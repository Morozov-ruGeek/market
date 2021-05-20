package ru.geekbrains.AMorozov.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.AMorozov.market.models.Order;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String description;
    private BigDecimal price;
    private Integer phoneNumber;
    private String address;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.price = order.getPrice();
        this.description = order.getItems().stream().map(o -> o.getProduct().getTitle() + " x" + o.getQuantity()).collect(Collectors.joining(", "));
        this.phoneNumber = order.getPhoneNumber();
        this.address = order.getAddress();
    }
}
