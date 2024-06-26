package com.example.client.repository;


import com.example.client.domain.UserInfo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserInfoRepository extends ReactiveCrudRepository<UserInfo, Long> {

    Mono<UserInfo> findByUserNameAndUserKey(String userName, String userKey);

}
