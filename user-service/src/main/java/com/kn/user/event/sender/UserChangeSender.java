package com.kn.user.event.sender;

import com.kn.user.domain.User;
import com.kn.user.event.model.UserChangeEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @ClassName: UserChangeSender
 * @Description TODO:
 * @Date: 2019/12/12 15:34
 * @Author: Kn
 */
@EnableBinding(Source.class)
@Component
public class UserChangeSender {
    @Resource
    private Source source;

    private void publishUserChange(User user, String type) {
        source.output().send(
                MessageBuilder.withPayload(
                        UserChangeEvent.builder()
                                .type(type)
                                .user(user)
                                .build()
                ).build()
        );
    }

    public void publishUserUpdateEvent(User user) {
        publishUserChange(user, "USER_UPDATE");
    }
    public void publishUserDeleteEvent(User user) {
        publishUserChange(user, "USER_DELETE");
    }
}
