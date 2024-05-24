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
@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "category_id")
    private long categoryId;

    @Column(name = "category_name")
    @NotNull(message = "Category Name is empty!")
    private String categoryName;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Boolean categoryStatus;
}
