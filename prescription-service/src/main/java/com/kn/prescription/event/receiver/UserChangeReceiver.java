package com.kn.prescription.event.receiver;

import com.kn.prescription.event.model.UserChangeEvent;
import com.kn.prescription.repository.user.UserRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @ClassName: UserChangeReceiver
 * @Description TODO:
 * @Date: 2019/12/12 16:39
 * @Author: Kn
 */
@EnableBinding(Sink.class)
@Slf4j
@Component
public class UserChangeReceiver {

    @Autowired
    private UserRedisRepository userRedisRepository;

    @StreamListener(Sink.INPUT)
    public void userChangeReceive(Message<UserChangeEvent> userChangeEventMessage) {
        log.info("接收到的信息为:"+userChangeEventMessage.getPayload());
        userChangeHandle(userChangeEventMessage.getPayload());

    }

    private void userChangeHandle(UserChangeEvent event) {
        /**
         * 常量应该用枚举类型
         */
        if ("USER_UPDATE".equals(event.getType())) {
            userRedisRepository.saveUser(event.getUser());
            log.info("更新对应用户缓存库:" + event.getUser());
        } else if ("USER_DELETE".equals(event.getType())) {
            userRedisRepository.deleteUser(event.getUser().getId());
            log.info("删除对应的用户缓存:"+event.getUser());
        } else {
            log.warn("UserChangeEvent类别处理异常："+event.getType());
        }
    }
}
