package ru.geekbrains.AMorozov.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.AMorozov.market.model.Product;

//@Repository
public interface iCartRepository extends JpaRepository <Product, Long> {
}
