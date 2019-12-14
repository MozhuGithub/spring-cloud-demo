package com.kn.prescription.repository.user;

import com.kn.prescription.model.User;

/**
 * @ClassName: UserRedisRepository
 * @Description TODO:
 * @Date: 2019/12/14 10:28
 * @Author: Kn
 */

public interface UserRedisRepository {

    void saveUser(User user);

    void deleteUser(String id);

    User findById(String id);
}
