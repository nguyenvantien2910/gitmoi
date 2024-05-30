package com.ra.projectmd03_nhom4.dto.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EditUserRequest {
    private Long id;

    @Size(min = 6, max = 100, message = "Username must be between 6 and 100 characters!")
    private String username;

    @Pattern(regexp = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$",message = "Invalid email format!")
    private String email;

    @NotNull(message = "Fullname is empty!")
    private String fullName;

    private String avatar;
    private MultipartFile file;

    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$",message = "Invalid phone format!")
    private String phone;

    @NotNull(message = "Address is empty!")
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;
}
