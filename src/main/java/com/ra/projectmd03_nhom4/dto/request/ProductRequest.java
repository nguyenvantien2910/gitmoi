package com.ra.projectmd03_nhom4.dto.request;

import com.ra.projectmd03_nhom4.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductRequest {
    private Long productId;
    private String sku;
    private String productName;
    private String productDescription;
    private Double unitPrice;
    private Integer stockQuantity;
//    private String image;
    private Category category;
    private Date createdAt;
    private Date updateAt;
    private MultipartFile file;
}
