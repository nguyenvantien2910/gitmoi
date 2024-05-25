package com.ra.projectmd03_nhom3.model;

import com.ra.projectmd03_nhom3.constant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "order_id")
    private Long orderId;

    @Column(unique = true, nullable = false, name = "serial_number")
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "note")
    @Size(max = 100, message = "Max character is 100 !")
    private String note;

    @Column(name = "receive_name")
    @Size(max = 100, message = "Max character is 100 !")
    private String receiveName;

    @Column(name = "receive_address")
    @Size(max = 255, message = "Max character is 255 !")
    private String receiveAddress;

    @Column(name = "receive_phone")
    @Size(max = 15, message = "Max charactor is 15 !")
    private String receivePhone;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @Column(name = "received_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date receivedAt;
}
