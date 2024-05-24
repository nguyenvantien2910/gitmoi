package com.ra.projectmd03_nhom3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id",unique = true, nullable = false)
    private Integer addressId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "full_address")
    @Size(max = 255,message = "Max charactor of note is 255 !")
    private String fullAddress;

    @Column(name = "phone")
    @Size(max = 15,message = "Max charactor of note is 255 !")
    private String phoneNumber;

    @Column(name = "receive_name")
    @Size(max = 15,message = "Max charactor of note is 255 !")
    private String receiveName;
}