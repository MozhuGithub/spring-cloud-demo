package com.kn.user.repository;

import com.kn.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: UserRepository
 * @Description TODO:
 * @Date: 2019/12/12 10:19
 * @Author: Kn
 */
public interface UserRepository extends JpaRepository<User, String> {

}
