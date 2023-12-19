package com.gkog.prototype.domain.user.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


public class ImageUploadDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Req {
        private MultipartFile profileImageData;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Res {

        private String profileImageUrl;
    }

}
