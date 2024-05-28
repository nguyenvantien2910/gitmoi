package com.ra.projectmd03_nhom4.dto.request;

import com.ra.projectmd03_nhom4.constant.OrderStatus;
import com.ra.projectmd03_nhom4.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderRequest {
    User user;
    String serialNumber;
    Double totalPrice;
    OrderStatus status;
    String note;
    String receiveAddress;
    String receivePhone;
    String receiveName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date updateDate;
 }
