package com.ra.projectmd03_nhom4.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BannerRequest {
    private Long id;
    private String title;
    private Boolean isDisplay;
    private MultipartFile file;
    private String url;
}
