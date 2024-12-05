package com.danal.batch.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Component
public class CustomJobExecutionListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.debug(">>>>>    [Job start]     name: {}", jobExecution.getJobInstance().getJobName());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		LocalDateTime startTime = jobExecution.getStartTime();
		LocalDateTime endTime = jobExecution.getEndTime();
		Duration diff = Duration.between(startTime, endTime);
		log.debug("<<<<<    [Job finish]    name: {} | status : {} | runTime : {}",
			jobExecution.getJobInstance().getJobName(),
			jobExecution.getStatus(),
			diff.getSeconds() + "s");
	}


}
