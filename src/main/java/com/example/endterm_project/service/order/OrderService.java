package com.example.endterm_project.service.order;

import com.example.endterm_project.dto.OrderDto;
import com.example.endterm_project.entity.Item;
import com.example.endterm_project.entity.Order;
import com.example.endterm_project.repository.IOrderRepository;
import com.example.endterm_project.repository.IItemRepository;
import com.example.endterm_project.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;
    private final IItemRepository itemRepository;

    public OrderService(IOrderRepository orderRepository, IUserRepository userRepository, IItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return toDto(order);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order();

        var user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Item> items = itemRepository.findAllById(orderDto.getItemIds());

        double totalPrice = items.stream().mapToDouble(Item::getPrice).sum();

        order.setUser(user);
        order.setItems(items);
        order.setTotal_price(totalPrice);
        order.setOrder_date(LocalDateTime.now());

        order = orderRepository.save(order);
        return toDto(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setOrderId(order.getOrder_id());
        dto.setUserId(order.getUser().getUser_id());
        dto.setItemIds(order.getItems().stream().map(Item::getItem_id).collect(Collectors.toList()));
        dto.setTotalPrice(order.getTotal_price());
        dto.setOrderDate(order.getOrder_date());
        return dto;
    }
}