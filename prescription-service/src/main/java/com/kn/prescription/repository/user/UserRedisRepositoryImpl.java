package com.kn.prescription.repository.user;

import com.kn.prescription.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * @ClassName: UserRedisRepositoryImpl
 * @Description TODO:
 * @Date: 2019/12/14 10:36
 * @Author: Kn
 */
@Repository
public class UserRedisRepositoryImpl implements UserRedisRepository {

    private static final String HASH_NAME = "user";

    private RedisTemplate<String, User> redisTemplate;

    private HashOperations<String, String, User> hashOperations;

    public UserRedisRepositoryImpl() {
        super();
    }

    @Autowired
    public UserRedisRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }
    @Override
    public void saveUser(User user) {
        this.hashOperations.put(HASH_NAME, user.getId(), user);
    }


    @Override
    public void deleteUser(String id) {
        this.hashOperations.delete(HASH_NAME, id);
    }

    @Override
    public User findById(String id) {
        return this.hashOperations.get(HASH_NAME, id);
    }
}
