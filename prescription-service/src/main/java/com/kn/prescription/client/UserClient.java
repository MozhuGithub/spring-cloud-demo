package com.kn.prescription.client;

import com.kn.prescription.model.User;
import com.kn.prescription.repository.user.UserRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: UserClient
 * @Description TODO:
 * @Date: 2019/12/14 15:36
 * @Author: Kn
 */
@Component
@Slf4j
public class UserClient {

    @Autowired
    private UserRedisRepository userRedisRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User getUserFromRemote(String userId) {
        ResponseEntity<User> responseEntity = this.restTemplate.exchange("http://user-service/user/{userId}",
                HttpMethod.GET, null, User.class, userId);
        log.info("远程调用User-Service返回的Http状态值:" + responseEntity.getStatusCodeValue());
        return responseEntity.getBody();
    }

    public User getUserFromCache(String userId) {
        User user = this.userRedisRepository.findById(userId);
        if (user == null) {
            user = getUserFromRemote(userId);
            userRedisRepository.saveUser(user);
        }
        return user;
    }
}
