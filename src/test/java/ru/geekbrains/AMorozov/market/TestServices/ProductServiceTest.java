package ru.geekbrains.AMorozov.market.TestServices;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.AMorozov.market.models.Product;
import ru.geekbrains.AMorozov.market.repositories.ProductRepository;
import ru.geekbrains.AMorozov.market.services.ProductService;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void findOneProductTest() {
        Product productFromDB = new Product();
        productFromDB.setId(1L);
        productFromDB.setTitle("Bread");
        productFromDB.setPrice(BigDecimal.valueOf(25.50));

        Mockito.doReturn(Optional.of(productFromDB))
                .when(productRepository)
                .findById(1L);

        Product productBread = productService.findById(1L).get();

        Assertions.assertNotNull(productBread);
        Assertions.assertEquals("Bread", productBread.getTitle());
        Assertions.assertEquals(BigDecimal.valueOf(25.50), productBread.getPrice());
        Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
    }
}
