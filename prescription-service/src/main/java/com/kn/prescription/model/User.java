package com.kn.prescription.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Description TODO: 服务对象-用户
 * @Date: 2019/12/12 10:12
 * @Author: Kn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User implements Serializable {

    private static final long serialVersionUID = 7073942930372788441L;
    private String id;

    private String userCode;

    private String userName;
}
