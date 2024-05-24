package com.ra.projectmd03_nhom3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id",unique = true, nullable = false)
    private Long orderId;

    @Column(name = "product_id",unique = true, nullable = false)
    private Long productId;
    @Column(name = "name")
    @Size(max = 100,message = "Max charactor of note is 100 !")
    private String productName;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "order_quantity")
    @Min(value = 0,message = "Order quantity must than 0 !")
    private Integer orderQuantity;
}
