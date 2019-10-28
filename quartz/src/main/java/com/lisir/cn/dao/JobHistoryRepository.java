package com.lisir.cn.dao;

import com.lisir.cn.entity.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
}
