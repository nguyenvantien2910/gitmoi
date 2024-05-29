package com.ra.projectmd03_nhom4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetailCompositeKey implements Serializable {
    private Long orderId;
    private Long productId;
}
