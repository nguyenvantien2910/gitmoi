package com.ra.projectmd03_nhom4.model;

import com.ra.projectmd03_nhom4.constant.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false,name = "role_id")
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}
