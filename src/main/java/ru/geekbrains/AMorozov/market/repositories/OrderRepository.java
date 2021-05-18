package ru.geekbrains.AMorozov.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.AMorozov.market.model.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
