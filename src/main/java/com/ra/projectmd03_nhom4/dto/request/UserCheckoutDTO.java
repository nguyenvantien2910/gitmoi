package com.ra.projectmd03_nhom4.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCheckoutDTO {
    private String address;
    private String phone;
    private String fullName;
    private String note;
}
