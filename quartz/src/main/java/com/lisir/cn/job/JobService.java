package com.lisir.cn.job;

import com.lisir.cn.entity.JobEntity;
import com.lisir.cn.entity.JobHistory;

public interface JobService {

    JobHistory saveJobHistory(JobHistory jobHistory);

    JobEntity queryJobById(Integer id);
}
