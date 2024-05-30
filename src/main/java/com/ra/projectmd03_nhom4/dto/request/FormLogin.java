package com.ra.projectmd03_nhom4.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FormLogin {
    @NotNull(message = "Username must not null")
    private String username;

    @NotNull(message = "Password must not null")
    private String password;
}
