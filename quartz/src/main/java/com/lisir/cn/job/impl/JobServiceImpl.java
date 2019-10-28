package com.lisir.cn.job.impl;

import com.lisir.cn.dao.JobHistoryRepository;
import com.lisir.cn.dao.JobRepository;
import com.lisir.cn.entity.JobEntity;
import com.lisir.cn.entity.JobHistory;
import com.lisir.cn.exception.JobException;
import com.lisir.cn.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Auther: liSir
 * @Date: 2019/9/17 20:12
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    @Override
    public JobHistory saveJobHistory(JobHistory jobHistory) {
        return jobHistoryRepository.save(jobHistory);
    }

    @Override
    public JobEntity queryJobById(Integer id) {
        Optional<JobEntity> jobEntity = jobRepository.findById(id);
        if (!jobEntity.isPresent()) {
            throw new JobException("JobEntity is null, jobId:{}" + id);
        }
        return jobEntity.get();
    }
}
