package ru.geekbrains.AMorozov.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.AMorozov.market.soap.soapproduct.SoapProduct;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SoapProductDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private String categoryTitle;

    public SoapProductDto (SoapProduct sProduct) {
        this.id = sProduct.getId();
        this.title = sProduct.getTitle();
        this.price = sProduct.getPrice();
        this.categoryTitle = sProduct.getSoapProductCategory();
    }
}
