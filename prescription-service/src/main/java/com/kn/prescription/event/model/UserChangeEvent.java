package com.kn.prescription.event.model;


import com.kn.prescription.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserChangeEvent
 * @Description TODO:
 * @Date: 2019/12/12 15:28
 * @Author: Kn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserChangeEvent {
    private String type;
    private User user;
}
