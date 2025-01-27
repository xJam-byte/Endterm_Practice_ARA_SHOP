package com.example.endterm_project.dto;

import com.example.endterm_project.entity.Category;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Long item_id;

    private String name;

    private String description;

    private Double price;

    private String manufacturer;

    private String picture;

    private Category category;

}
