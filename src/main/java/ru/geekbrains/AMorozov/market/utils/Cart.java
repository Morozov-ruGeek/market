package ru.geekbrains.AMorozov.market.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.geekbrains.AMorozov.market.dtos.OrderItemDto;
import ru.geekbrains.AMorozov.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.AMorozov.market.models.OrderItem;
import ru.geekbrains.AMorozov.market.models.Product;
import ru.geekbrains.AMorozov.market.services.ProductService;


import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart implements Serializable {
    private List<OrderItemDto> items;
    private BigDecimal sum;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
        sum = BigDecimal.ZERO;
    }

    public void decrementProduct(Long id) {
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemDto o = iter.next();
            if (o.getProductId().equals(id)) {
                o.changeQuantity(-1);
                if (o.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public boolean addToCart(Long id) {
        for (OrderItemDto o : items) {
            if (o.getProductId().equals(id)) {
                o.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void addToCart(Product product) {
        items.add(new OrderItemDto(product));
        recalculate();
    }


    public void clear() {
        items.clear();
        recalculate();
    }

    public void removeProduct(Long id) {
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemDto o = iter.next();
            if (o.getProductId().equals(id)) {
                iter.remove();
                recalculate();
                return;
            }
        }
    }

    public void recalculate() {
        sum = BigDecimal.ZERO;
        for (OrderItemDto oi : items) {
            sum = sum.add(oi.getPrice());
        }
    }

    public void merge(Cart another) {
        for (OrderItemDto anotherItem : another.items) {
            boolean merged = false;
            for (OrderItemDto myItem : items) {
                if (myItem.getProductId().equals(anotherItem.getProductId())) {
                    myItem.changeQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                items.add(anotherItem);
            }
        }
        recalculate();
        another.clear();
    }
}
