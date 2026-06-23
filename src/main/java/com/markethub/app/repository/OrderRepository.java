package com.markethub.app.repository;

import com.markethub.app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> getOrderByOwnerUserId(Long userId);
}
