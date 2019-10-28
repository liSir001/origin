package com.lisir.cn.dao;

import com.lisir.cn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: liSir
 */
public interface UserDal extends JpaRepository<User, Integer> {
}
