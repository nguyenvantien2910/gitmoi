package com.ra.projectmd03_nhom4.model;

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
    @EmbeddedId
    private OrderDetailCompositeKey compositeKey;
    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "product_id")
    private Product product;

    @Column(name = "name")
    @Size(max = 100,message = "Max character is 100 !")
    private String productName;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "order_quantity")
    @Min(value = 1,message = "Order quantity must than 0 !")
    private Integer orderQuantity;
}
