package ru.geekbrains.AMorozov.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.geekbrains.AMorozov.market.models.OrderItem;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public OrderItemDto(OrderItem orderItem) {
        this.productId = orderItem.getProduct().getId();
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.pricePerProduct = orderItem.getPricePerProduct();
        this.price = orderItem.getPrice();
    }
}
