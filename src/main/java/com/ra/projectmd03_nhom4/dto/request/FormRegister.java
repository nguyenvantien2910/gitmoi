package com.ra.projectmd03_nhom4.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FormRegister {
    @NotNull(message = "Full name must not null")
    private String fullName;
    @NotNull(message = "Username must not null")
    private String username;
    @NotNull(message = "Password must not null")
    private String password;
    @NotNull(message = "Address must not null")
    private String address;
    @NotNull(message = "Email must not null")
    private String email;
}
