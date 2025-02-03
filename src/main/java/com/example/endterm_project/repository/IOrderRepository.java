package com.example.endterm_project.repository;

import com.example.endterm_project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {

}
