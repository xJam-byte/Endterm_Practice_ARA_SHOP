package com.example.endterm_project.service.item;

import com.example.endterm_project.dto.ItemDto;
import com.example.endterm_project.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IItemService {
    List<ItemDto> getAllItems();
    ItemDto getItemById(Long id);
    ItemDto createItem(ItemDto itemDto);
    ItemDto updateItem(Long id, ItemDto itemDto);
    void deleteItem(Long id);
}
