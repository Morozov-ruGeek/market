package ru.geekbrains.AMorozov.market.TestControllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.AMorozov.market.models.Category;
import ru.geekbrains.AMorozov.market.models.Product;
import ru.geekbrains.AMorozov.market.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void getProductById() throws Exception {

        Product product = new Product();
        product.setId(1L);
        product.setTitle("Fish");
        Category category = new Category();
        category.setTitle("Food");
        product.setCategory(category);
        product.setPrice(BigDecimal.valueOf(370.5));
        Optional<Product> initProduct = Optional.of(product);
        given(productRepository.findById(1L)).willReturn(Optional.of(product));
//        given(productRepository.findById(1L).willReturn());

        mvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].title", is(initProduct.get().getTitle())));
    }
}
