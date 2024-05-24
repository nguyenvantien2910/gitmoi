package com.ra.projectmd03_nhom3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "shoping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shoping_cart_id",unique = true, nullable = false)
    private Integer shoppingCartId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "order_quantity")
    @Min(value = 0,message = "Order quantity must than 0 !")
    private Integer orderQuantity;
}
