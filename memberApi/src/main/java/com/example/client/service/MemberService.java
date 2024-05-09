package com.example.client.service;


import com.example.client.domain.UserInfo;
import com.example.client.dto.JwtDto;
import com.example.client.dto.UserInfoDto;
import com.example.client.exception.DataException;
import com.example.client.jwt.TokenProvider;
import com.example.client.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final UserInfoRepository userInfoRepository;
    private final TokenProvider jwtProvider;

    public Mono<?> perform(UserInfoDto userInfoDto) {

        log.info("SEARCHING :: USER NAME : " + userInfoDto.getUserName() + ", USER KEY : " + userInfoDto.getUserKey());

        return getUserInfo(userInfoDto.getUserName(), userInfoDto.getUserKey()).map(data ->
                JwtDto.builder()
                        .resultCode(0000)
                        .resultMsg("success")
                        .token(jwtProvider.createToken(data.getUserName()))
                        .build()
        );
    }

    private Mono<UserInfo> getUserInfo(String userName, String userKey) {
        return userInfoRepository.findByUserNameAndUserKey(userName, userKey)
                .switchIfEmpty(Mono.error((new DataException(7999, "NOT EXIST USER INFO"))));
    }
}
