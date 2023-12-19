package com.gkog.prototype.domain.user.service;

import com.gkog.prototype.domain.user.dao.UserRepository;
import com.gkog.prototype.domain.user.dto.ImageUploadDto;
import com.gkog.prototype.domain.user.dto.JoinDto;
import com.gkog.prototype.domain.user.entity.User;
import com.gkog.prototype.global.utils.GCSUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserJoinService {

    private final UserRepository userRepository;

    private final GCSUploader gcsUploader;

    @Value("{app.auth.joiningCode}")
    private String joiningCode;

    @Transactional
    public JoinDto.BasicRes create(JoinDto.BasicReq userReq) {

        //check
        if(!userReq.getCodeString().equals(joiningCode)) {
            // throw business error
        }
        User user = userReq.toEntity();

        JoinDto.BasicRes res = new JoinDto.BasicRes(userRepository.save(user).getUserId());

        return res;
    }

    // 닉네임 중복 확인
    public JoinDto.CheckDuplicateRes checkNicknameDuplicated(String nickname) {
        Optional<User> user = getUserByNickname(nickname);
        JoinDto.CheckDuplicateRes res = new JoinDto.CheckDuplicateRes(user.isEmpty());
        return res;
    }

    public Optional<User> getUserByNickname(String nickname) {
        Optional<User> user = userRepository.findByNickName(nickname);
        return user;
    }

    // 프로필 이미지 url 저장
    public ImageUploadDto.Res saveProfileImage(ImageUploadDto.Req req) throws IOException {

        String imageUrl = gcsUploader.uploadImage(req.getProfileImageData());
        ImageUploadDto.Res res = new ImageUploadDto.Res(imageUrl);
        return res;
    }
}
