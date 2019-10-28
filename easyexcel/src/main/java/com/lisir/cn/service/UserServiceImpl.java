package com.lisir.cn.service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.lisir.cn.dao.UserDal;
import com.lisir.cn.dto.UserDTO;
import com.lisir.cn.dto.UserRequestDTO;
import com.lisir.cn.entity.User;
import com.lisir.cn.exception.EasyExcelException;
import com.lisir.cn.util.EasyExcelUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: liSir
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDal userDal;

    public List<User> list() {
        return userDal.findAll();
    }

    public void export() {
        final String filePath = "E:\\Temp\\用户.xlsx";
        final String sheetName = "用户表";
        final List<User> userList = list();
        EasyExcelUtils.writeExcelByModel(filePath, sheetName, userList, User.class);
    }

    public void exportExcel() {
        // 文件输出路径
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("E:\\Temp\\test.xlsx");
            ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
            Sheet s = new Sheet(1, 0, User.class);
            // 设置名称
            s.setSheetName("用户表");
            /**
             * 写数据到 Writer 上下文中
             * 参数1: 数据库查询的数据list集合
             * 参数2: 要写入的目标 sheet
             */
            writer.write(list(), s);
            // 将上下文中的最终 outputStream 写入到指定文件中
            writer.finish();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public UserDTO createUser(UserRequestDTO userRequestDTO) {
        final User user = new User();
        user.setName(userRequestDTO.getName());
        user.setPassword(userRequestDTO.getPassword());
        user.setAge(userRequestDTO.getAge());
        User u = userDal.save(user);
        final UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(u, userDTO);
        return userDTO;
    }

    @Override
    public UserDTO updateUser(UserRequestDTO userRequestDTO) {
        final Integer userId = userRequestDTO.getId();
        Optional<User> optional = userDal.findById(userId);
        if (!optional.isPresent()) {
            throw new EasyExcelException("Can not find user with id, id: " + userId);
        }
        final User user = optional.get();
        user.setName(userRequestDTO.getName());
        user.setPassword(userRequestDTO.getPassword());
        user.setAge(userRequestDTO.getAge());
        User u = userDal.save(user);
        final UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(u, userDTO);
        return userDTO;
    }
}