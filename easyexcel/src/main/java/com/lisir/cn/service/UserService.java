package com.lisir.cn.service;

import com.lisir.cn.dto.UserDTO;
import com.lisir.cn.dto.UserRequestDTO;
import com.lisir.cn.entity.User;
import java.util.List;

public interface UserService {

    List<User> list();

    void export();

    void exportExcel();

    UserDTO createUser(UserRequestDTO userRequestDTO);

    UserDTO updateUser(UserRequestDTO userRequestDTO);
}
