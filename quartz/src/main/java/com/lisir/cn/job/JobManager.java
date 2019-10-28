package com.lisir.cn.job;

import com.lisir.cn.dao.JobRepository;
import com.lisir.cn.entity.JobEntity;
import com.lisir.cn.exception.JobException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * @Auther: liSir
 * @Date: 2019/9/17 19:33
 */
@Component
@Slf4j
public class JobManager implements CommandLineRunner {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            List<JobExecutionContext> currentlyExecutingJobs = scheduler.getCurrentlyExecutingJobs();
            for (JobExecutionContext executingJob : currentlyExecutingJobs) {
                final JobKey jobKey = executingJob.getJobDetail().getKey();
                final String jobName = jobKey.getName();
                JobEntity job = jobRepository.findByJobName(jobName);
                if (job == null) {
                    scheduler.deleteJob(jobKey);
                    log.info("Delete job for name:{}", jobName);
                }
            }
            List<JobEntity> jobList = jobRepository.findAll();
            for (JobEntity job : jobList) {
                final Integer jobId = job.getId();
                final String jobCron = job.getCron();
                final String jobName = job.getJobName();
                final Boolean jobStatus = job.getStatus();
                final String executionName = job.getExecutionName();

                JobKey jobKey = new JobKey(jobName);
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                if (jobDetail == null && jobStatus) {
                    // 添加job
                    jobDetail = JobBuilder.newJob(JobRunner.class).withIdentity(jobName).build();
                    jobDetail.getJobDataMap().put(JobRunner.JOB_DATA_JOB_ID, jobId);
                    jobDetail.getJobDataMap().put(JobRunner.JOB_DATA_JOB_NAME, jobName);
                    jobDetail.getJobDataMap().put(JobRunner.JOB_DATA_EXECUTOR_NAME, executionName);
                    Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName).withSchedule(CronScheduleBuilder.cronSchedule(jobCron)).build();
                    scheduler.scheduleJob(jobDetail, trigger);
                    log.info("Create job for id:{}, name:{}, cron:{}", jobId, jobName, jobCron);
                } else if (jobDetail != null && !jobStatus) {
                    // 删除job
                    scheduler.deleteJob(jobKey);
                    log.info("Delete job for id:{}, name:{}, cron:{}", jobId, jobName, jobCron);
                }
            }
        } catch (SchedulerException e) {
            throw new JobException("Scheduler execute fail", e);
        }
    }
}
