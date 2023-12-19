package com.gkog.prototype.domain.user.service;

import com.gkog.prototype.domain.user.dao.UserRepository;
import com.gkog.prototype.domain.user.dto.JoinDto;
import com.gkog.prototype.domain.user.entity.User;
import com.gkog.prototype.domain.user.enums.ProviderType;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.gkog.prototype.domain.user.service.UserJoinService;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource(properties = {"spring.config.location = classpath:application-test-h2.yml"})
@SpringBootTest
@Transactional
@Slf4j
public class UserJoinServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserJoinService userJoinService;

    private User mockUser;

    @Autowired
    EntityManager em;

    @BeforeEach
    public void init() {
        mockUser = User.builder()
                .nickName("Daniel")
                .email("gdsc@gmail.com")
                .profileImgUrl("dsafsdf")
                .providerType(ProviderType.APPLE.getName())
                .build();

        userRepository.save(mockUser);
    }

    @Test
    @DisplayName("Test: createUser")
    public void createTest() {
        JoinDto.BasicReq mockReq = JoinDto.BasicReq.builder()
                .codeString("1234")
                .email("hihi@gmail.com")
                .nickname("success")
                .profileImgUrl("")
                .build();

        JoinDto.BasicRes expectedRes = JoinDto.BasicRes.builder()
                .id(2L)
                .build();

        User expectedUserEntity = User.builder()
                .userId(2L)
                .email("hihi@gmail.com")
                .nickName("success")
                .profileImgUrl("")
                .providerType(ProviderType.APPLE.getName())
                .build();

        JoinDto.BasicRes actualRes = userJoinService.create(mockReq);

        User actualUserEntity = userRepository.findByNickName("success").orElseThrow();

        Assertions.assertAll(
                () -> assertEquals(actualRes, expectedRes),
                () -> assertEquals(actualUserEntity, expectedUserEntity)
        );
    }


}
