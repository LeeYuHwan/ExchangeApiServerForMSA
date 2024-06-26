package com.example.client.controller;

import com.example.client.dto.UserInfoDto;
import com.example.client.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/ms2/api/v1/jwt")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/token")
    public Mono<?> createJWTToken(@RequestBody UserInfoDto userInfoDto){
        return memberService.perform(userInfoDto);
    }

}
