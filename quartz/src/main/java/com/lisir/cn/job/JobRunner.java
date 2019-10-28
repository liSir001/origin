package com.lisir.cn.job;

import com.lisir.cn.dao.JobHistoryRepository;
import com.lisir.cn.entity.JobEntity;
import com.lisir.cn.entity.JobHistory;
import com.lisir.cn.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Auther: liSir
 * @Date: 2019/9/17 19:44
 */
@Slf4j
public class JobRunner extends QuartzJobBean {

    public static final String JOB_DATA_JOB_ID = "jobId";
    public static final String JOB_DATA_JOB_NAME = "jobName";
    public static final String JOB_DATA_EXECUTOR_NAME = "executorName";

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        Integer jobId = (Integer) jobDataMap.get(JOB_DATA_JOB_ID);
        String jobName = (String) jobDataMap.get(JOB_DATA_JOB_NAME);
        String executorName = (String) jobDataMap.get(JOB_DATA_EXECUTOR_NAME);

        JobService service = SpringContextUtils.getBean(JobService.class);
        JobEntity jobEntity = service.queryJobById(jobId);
        JobHistory jobHistory = new JobHistory();
        jobHistory.setJob(jobEntity);
        try {
            log.info("Job started, jobId:{}, jobName:{}, executorName:{}", jobId, jobName, executorName);
            JobExecutor jobExecutor = (JobExecutor) SpringContextUtils.getBean(executorName);
            jobExecutor.run();
            log.info("Job finished, jobId:{}, jobName:{}, executorName:{}", jobId, jobName, executorName);
            jobHistory.setStatus(true);
        } catch (Exception e) {
            jobHistory.setStatus(false);
            log.warn("JobRunner execute exception", e);
        }
        log.info("Save to jobHistory start, request:{}", jobHistory);
        JobHistory history = service.saveJobHistory(jobHistory);
        log.info("Save to jobHistory end, response:{}", history);
    }
}
