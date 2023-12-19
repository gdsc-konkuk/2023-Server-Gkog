package com.gkog.prototype.domain.user.dto;

import com.gkog.prototype.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class JoinDto {

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BasicReq {

        private String codeString;

        private String email;

        private String nickname;

        private String profileImgUrl;

        public User toEntity() {
            User user = User.builder()
                    .email(email)
                    .nickName(nickname)
                    .profileImgUrl(profileImgUrl)
                    .build();
            return user;
        }
    }
    


    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BasicRes {
        private Long id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CheckDuplicateRes {
        private Boolean isDuplicated;
    }



}
