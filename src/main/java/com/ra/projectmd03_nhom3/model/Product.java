package com.ra.projectmd03_nhom3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false,name = "product_id")
    private Long productId;

    @Column(name = "product_name",unique = true,nullable = false)
    @Size(min = 1, max = 100,message = "Product Name must be between 1 and 100 characters!")
    @NotNull(message = "Product Name is emty!")
    private String productName;

    @Column(name = "sku",unique = true)
    private String sku;

    @Column(name = "description")
    private String productDescription;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "stock_quantity")
    @Min(value = 1,message = "Stock quantity must be then 0 !")
    private Integer stockQuantity;

    @Column(name = "image")
    private String image;

    @Column(name = "product_id")
    private Category category;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateAt;
}
