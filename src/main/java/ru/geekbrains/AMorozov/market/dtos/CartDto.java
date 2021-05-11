package ru.geekbrains.AMorozov.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.AMorozov.market.model.Cart;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private List<ProductDto> cartProductsDto;
    private int sum;

    public CartDto(Cart cart){
        sum = cart.getSum();
        this.cartProductsDto = cart.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }
}
