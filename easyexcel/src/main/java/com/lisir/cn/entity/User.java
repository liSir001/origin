package com.lisir.cn.entity;

import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseRowModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ExcelProperty(value = "姓名", index = 0) // value是表头名称，index是第几列
    @Column
    private String name;

    @ExcelProperty(value = "密码", index = 1)
    @Column
    private String password;

    @ExcelProperty(value = "年龄", index = 2)
    @Column
    private Integer age;
}