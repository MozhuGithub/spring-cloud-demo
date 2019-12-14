package com.kn.user.service;

import com.kn.user.domain.User;
import com.kn.user.event.sender.UserChangeSender;
import com.kn.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: UserService
 * @Description TODO:
 * @Date: 2019/12/12 10:21
 * @Author: Kn
 */
@Service
public class UserService {

    @Autowired
    private UserChangeSender userChangeSender;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public ResponseEntity<User> findById(String id) {
        return ResponseEntity.of(this.userRepository.findById(id));
    }

    public ResponseEntity<User> createUser(User user) {
        user.setId(null);
        return ResponseEntity.ok(this.userRepository.save(user));
    }

    /**
     * @Description TODO: 更新用户信息并发送用户更新事件到到处方服务
     * @Param: [id, user]
     * @Return: org.springframework.http.ResponseEntity<com.kn.user.domain.User>
     * @Author: Kn
     * @Date: 2019/12/12 15:56
     */
    public ResponseEntity<User> updateUserById(String id, User user) {
        User updateById = this.userRepository.findById(id)
                .get()
                .toBuilder()
                .userCode(user.getUserCode())
                .userName(user.getUserName())
                .build();
        this.userRepository.save(updateById);
        userChangeSender.publishUserUpdateEvent(updateById);
        return ResponseEntity.ok(updateById);
    }

    /**
     * @Description TODO: 删除用户信息并发送用户删除事件通知到处方服务
     * @Param: [id]
     * @Return: org.springframework.http.ResponseEntity<com.kn.user.domain.User>
     * @Author: Kn
     * @Date: 2019/12/12 15:57
     */
    public ResponseEntity<User> deleteUserById(String id) {
        User findById = this.userRepository.findById(id).get();
        this.userRepository.delete(findById);
        userChangeSender.publishUserDeleteEvent(findById);
        return ResponseEntity.ok(null);
    }

}
