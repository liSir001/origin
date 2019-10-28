package com.lisir.cn.dao;

import com.lisir.cn.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobEntity, Integer> {

    JobEntity findByJobName(String jobName);
}
