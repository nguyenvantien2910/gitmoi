package com.ra.projectmd03_nhom4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voucherId;

    @Column(unique=true,nullable = false)
    private String voucherCode;

    @Column(nullable = false)
    private double discount;

    @Column(nullable = false)
    private LocalDateTime expiryDate;
}
