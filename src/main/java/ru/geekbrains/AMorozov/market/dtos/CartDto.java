package ru.geekbrains.AMorozov.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.AMorozov.market.utils.Cart;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private List<OrderItemDto> cartProductsDto;
    private BigDecimal sum;

    public CartDto(Cart cart){
        this.cartProductsDto = cart.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.sum = cart.getSum();
    }
}
