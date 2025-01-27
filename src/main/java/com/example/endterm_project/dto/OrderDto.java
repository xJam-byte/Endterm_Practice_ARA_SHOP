package com.example.endterm_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long orderId;

    private Long userId;

    private List<Long> itemIds;

    private Double totalPrice;

    private LocalDateTime orderDate;
}