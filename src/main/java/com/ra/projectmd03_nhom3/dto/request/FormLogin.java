package com.ra.projectmd03_nhom3.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FormLogin {
    private String username;
    private String password;
}
