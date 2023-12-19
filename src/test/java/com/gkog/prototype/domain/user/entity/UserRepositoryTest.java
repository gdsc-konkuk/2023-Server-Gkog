package com.gkog.prototype.domain.user.entity;

import com.gkog.prototype.domain.user.dao.UserRepository;
import com.gkog.prototype.domain.user.enums.ProviderType;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.NoSuchElementException;


@TestPropertySource(properties = {"spring.config.location = classpath:application-test-h2.yml"})
@SpringBootTest
@Transactional
@Slf4j
public class UserRepositoryTest {

    private static User testUser;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        testUser = User.builder()
                .email("gdsc@gmail.com")
                .nickName("gdsc")
                .profileImgUrl("")
                .providerType(ProviderType.APPLE.getName())
                .build();
    }

    @Test
    @DisplayName("Test : findByNickname")
    public void testFindByNickName() {
        //given
        userRepository.save(testUser);
        String targetNickname = "gdsc";
        //when
        User user = userRepository.findByNickName(targetNickname).stream().findAny().get();

        //then
        log.info(user.getProviderType());

        Assertions.assertNotNull(testUser);
        Assertions.assertEquals(testUser.getEmail(), user.getEmail());
        Assertions.assertEquals(testUser.getNickName(), user.getNickName());
        Assertions.assertEquals(testUser.getProviderType(), user.getProviderType());
    }

    @Test
    @DisplayName("Test : findByNickname; no exist")
    public void testFindByNicknameFail() {

        userRepository.save(testUser);
        String targetNickname = "fail";

        Exception exception = Assertions.assertThrows(NoSuchElementException.class, ()->{
            User user = userRepository.findByNickName(targetNickname).stream().findAny().get();

        });
    }
}
