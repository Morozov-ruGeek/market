package ru.geekbrains.AMorozov.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.AMorozov.market.model.Product;
import ru.geekbrains.AMorozov.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findOneById (Long id){
        return productRepository.findById(id);
    }

    public void deleteOneById (Long id){
        productRepository.deleteById(id);
    }


    public Product createNewProduct(Product product){
        return productRepository.save(product);
    }
}
