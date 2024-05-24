package com.ra.projectmd03_nhom3.model;

import com.ra.projectmd03_nhom3.constant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false,name = "order_id")
    private Integer orderId;

    @Column(unique = true, nullable = false,name = "serial_number")
    private String serialNumber;

    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "note")
    @Size(max = 100,message = "Max charactor of note is 100 !")
    private String note;

    @Column(name = "receive_name")
    @Size(max = 100,message = "Max charactor of note is 100 !")
    private String receiveName;

    @Column(name = "receive_address")
    @Size(max = 100,message = "Max charactor of note is 100 !")
    private String receiveAddress;

    @Column(name = "receive_phone")
    @Size(max = 15,message = "Max charactor of note is 100 !")
    private String receivePhone;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateAt;
}
