package com.lisir.cn.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@Entity
@Table(name = "job_history")
@EntityListeners({AuditingEntityListener.class})
public class JobHistory {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobEntity job;

    @Column
    private Boolean status;

    @CreatedDate
    @Column
    @Temporal(TIMESTAMP)
    private Date createdTime;

    @LastModifiedDate
    @Column
    @Temporal(TIMESTAMP)
    private Date updatedTime;
}