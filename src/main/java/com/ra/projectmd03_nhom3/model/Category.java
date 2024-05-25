package com.ra.projectmd03_nhom3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    @NotNull(message = "Category name is empty!")
    private String categoryName;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @NotNull(message = "Category status is empty!")
    private Boolean categoryStatus;
}
