package com.example.endterm_project.repository;

import com.example.endterm_project.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository extends JpaRepository<Item, Long> {
    Item findByName(String name);
}
