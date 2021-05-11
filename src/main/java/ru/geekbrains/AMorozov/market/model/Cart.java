package ru.geekbrains.AMorozov.market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private List<Product> products;
    private int sum;

    @PostConstruct
    private void init() {
        products = new ArrayList<>(Arrays.asList());
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public void save(Product product) {
        products.add(product);
        sum += product.getPrice();
    }

    public void deleteById(Long id){
        for (Product product : products) {
            if(product.getId() == id){
                sum -= product.getPrice();
                products.remove(product);
                return;
            }
        }
    }

    public void clearCart(){
        products.clear();
        sum = 0;
    }

}
