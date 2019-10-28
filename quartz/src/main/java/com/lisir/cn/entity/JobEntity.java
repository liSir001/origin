package com.lisir.cn.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "job")
@EntityListeners({ AuditingEntityListener.class })
public class JobEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String jobName;

    @Column
    private String topicName;

    @Column(columnDefinition = "LONGTEXT")
    private String jobConfig;

    @Column
    private String cron;

    @Column
    private Boolean status;

    @Column(name = "execution_bean")
    private String executionName;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

}
