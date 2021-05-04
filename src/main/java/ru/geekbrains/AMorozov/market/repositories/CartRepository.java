package ru.geekbrains.AMorozov.market.repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.AMorozov.market.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class CartRepository {
    private List<Product> products;

    @PostConstruct
    private void init() {
        products = new ArrayList<>(Arrays.asList());
    }

    public List<Product> findAll() {
        return products;
    }

    public void save(Product product) {
        products.add(product);
    }

    public void clearCart(){
        products.clear();
    }
}
