package ru.geekbrains.AMorozov.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.AMorozov.market.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
