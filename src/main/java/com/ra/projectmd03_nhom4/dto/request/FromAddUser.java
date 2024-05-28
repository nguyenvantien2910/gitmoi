package com.ra.projectmd03_nhom4.dto.request;

import com.ra.projectmd03_nhom4.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FromAddUser {
    @Size(min = 6, max = 100, message = "Username must be between 6 and 100 characters!")
    private String username;

    @Pattern(regexp = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$", message = "Invalid email format!")
    private String email;

    @NotNull(message = "Fullname is empty!")
    private String fullName;

    private String password;

    private String avatar;

    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "Invalid phone format!")
    private String phone;

    @NotNull(message = "Address is empty!")
    private String address;

    private Set<Role> roles = new HashSet<>();
}
