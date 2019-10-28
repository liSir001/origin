package com.lisir.cn.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Company {

    private Integer id;

    private String name;

    private Integer age;

    private String address;

    private Float salary;

    private Date joinDate;
}
