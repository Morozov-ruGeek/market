package ru.geekbrains.AMorozov.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.AMorozov.market.models.Order;
import ru.geekbrains.AMorozov.market.models.User;


import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}