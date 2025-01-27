package com.example.endterm_project.service.order;

import com.example.endterm_project.dto.OrderDto;
import com.example.endterm_project.entity.Order;

import java.util.List;

public interface IOrderService {
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(Long id);
    OrderDto createOrder(OrderDto orderDto);
    void deleteOrder(Long id);
}