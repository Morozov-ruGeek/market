package ru.geekbrains.AMorozov.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.AMorozov.market.model.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;

    @Size(min = 2, max = 255, message = "Title size: 4-255")
    private String title;

    @Min(value = 1, message = "Min price = 1")
    private BigDecimal price;

    private String categoryTitle;

    public ProductDto (Product product){
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategory().getTitle();
    }
}
