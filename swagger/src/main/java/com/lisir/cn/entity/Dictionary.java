package com.lisir.cn.entity;

import com.lisir.cn.enums.ValueType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "dictionary")
@EqualsAndHashCode(callSuper = true)
public class Dictionary extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "value_type")
    @Enumerated(EnumType.STRING)
    private ValueType valueType;

    @Column
    private String tag;
}