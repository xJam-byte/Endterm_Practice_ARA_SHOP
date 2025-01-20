package com.example.endterm_project.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String description;

    @Column(nullable=false)
    private Double price;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String manufacturer;

    @Column(nullable=false)
    private String picture;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category categoryId;
}
