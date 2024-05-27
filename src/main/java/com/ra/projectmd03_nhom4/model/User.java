package com.ra.projectmd03_nhom4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    @Size(min = 6, max = 100, message = "Username must be between 6 and 100 characters!")
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    @Pattern(regexp = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$",message = "Invalid email format!")
    private String email;

    @Column(name = "fullname", nullable = false)
    @NotNull(message = "Fullname is empty!")
    private String fullName;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "phone",unique = true)
    @Pattern(regexp = "^(0(3|5|7|8|9)\\\\d{8}|02\\\\d{8,9})$",message = "Invalid phone format!")
    private String phone;

    @Column(name = "address", nullable = false)
    @NotNull(message = "Address is empty!")
    private String address;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

//    @PrePersist
//    protected void onCreate() {
//        this.createdAt = new Date();
//    }

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;
}
