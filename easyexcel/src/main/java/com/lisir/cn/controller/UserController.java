package com.lisir.cn.controller;

import com.lisir.cn.dto.UserDTO;
import com.lisir.cn.dto.UserRequestDTO;
import com.lisir.cn.entity.User;
import com.lisir.cn.group.Create;
import com.lisir.cn.group.Update;
import com.lisir.cn.service.UserService;
import com.lisir.cn.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新增用户
     * @param userRequestDTO
     * @return
     */
    @PostMapping("add")
    public UserDTO CreateUser(@Validated({Create.class, Default.class})
                              @RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }

    /**
     * 更新用户
     * @param userRequestDTO
     * @return
     */
    @PutMapping("update")
    public UserDTO UpdateUser(@Validated({Update.class, Default.class})
                              @RequestBody UserRequestDTO userRequestDTO) {
        return userService.updateUser(userRequestDTO);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @GetMapping("list")
    public List<User> getAllUser() {
        return userService.list();
    }

    /**
     * 导出用户信息到excel
     */
    @GetMapping("export")
    public void exportExcel() {
        userService.export();
    }
}
