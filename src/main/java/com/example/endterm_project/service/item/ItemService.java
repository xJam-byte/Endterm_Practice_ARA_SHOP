package com.example.endterm_project.service.item;

import com.example.endterm_project.repository.IItemRepository;
import com.example.endterm_project.dto.ItemDto;
import com.example.endterm_project.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ItemService implements IItemService {
    private IItemRepository itemRepository;

    public ItemService(IItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ItemDto getItemById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        return toDto(item);
    }

    @Override
    public ItemDto createItem(ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setDescription(itemDto.getDescription());
        item.setManufacturer(itemDto.getManufacturer());
        item.setPicture(itemDto.getPicture());
        item.setCategoryId(itemDto.getCategory());
        item = itemRepository.save(item);
        return toDto(item);
    }

    @Override
    public ItemDto updateItem(Long id, ItemDto itemDto) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setDescription(itemDto.getDescription());
        item.setManufacturer(itemDto.getManufacturer());
        item.setPicture(itemDto.getPicture());
        item.setCategoryId(itemDto.getCategory());
        item = itemRepository.save(item);
        return toDto(item);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    private ItemDto toDto(Item item) {
        ItemDto dto = new ItemDto();
        dto.setItem_id(item.getItem_id());
        dto.setName(item.getName());
        dto.setPrice(item.getPrice());
        return dto;
    }
}
