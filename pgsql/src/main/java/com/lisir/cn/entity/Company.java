package com.lisir.cn.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "company")
public class Company {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private String address;

    @Column
    private Float salary;

    @Column(name = "join_date")
    private Date joinDate;
}
