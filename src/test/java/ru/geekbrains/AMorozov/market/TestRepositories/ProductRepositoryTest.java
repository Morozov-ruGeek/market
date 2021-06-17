package ru.geekbrains.AMorozov.market.TestRepositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.AMorozov.market.models.Category;
import ru.geekbrains.AMorozov.market.models.Product;
import ru.geekbrains.AMorozov.market.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void productRepositoryTest() {
        Product product = new Product();
        product.setId(4L);
        product.setTitle("Fish");
        product.setPrice(BigDecimal.valueOf(70.45));
        Category category = new Category();
        category.setTitle("Food");
        product.setCategory(category);
        entityManager.persist(product); //todo fail test
        entityManager.flush();

        List<Product> productList = productRepository.findAll();

        Assertions.assertEquals(4, productList.size());
        Assertions.assertEquals("Fish", productList.get(1).getTitle());
    }

    @Test
    public void initDbTest() {
        List<Product> productList = productRepository.findAll();
        Assertions.assertEquals(3, productList.size());
    }
}
