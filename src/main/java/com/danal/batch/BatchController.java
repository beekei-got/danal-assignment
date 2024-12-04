package com.danal.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class BatchController {

	private final JobLauncher jobLauncher;
	private final Job normalRestaurantBatchJob;

	@GetMapping("/batch/start")
	public ModelAndView batchStart() {
		return new ModelAndView("batch-start");
	}

	@GetMapping("/batch")
	public ModelAndView batch() {
		ModelAndView modelAndView = new ModelAndView("batch-end");
		try {
			JobExecution jobExecution = jobLauncher
				.run(normalRestaurantBatchJob, new JobParametersBuilder().toJobParameters());
			jobExecution.addFailureException(new Throwable("error"));
			modelAndView.addObject("jobId", jobExecution.getJobId());
			modelAndView.addObject("jobName", jobExecution.getJobInstance().getJobName());
			modelAndView.addObject("jobStartTime", jobExecution.getStartTime());
			modelAndView.addObject("jobEndTime", jobExecution.getEndTime());
			modelAndView.addObject("jobStatus", jobExecution.getStatus());
		} catch (JobExecutionAlreadyRunningException e) {
			throw new RuntimeException(e);
		} catch (JobRestartException e) {
			throw new RuntimeException(e);
		} catch (JobInstanceAlreadyCompleteException e) {
			throw new RuntimeException(e);
		} catch (JobParametersInvalidException e) {
			throw new RuntimeException(e);
		}
		return modelAndView;
	}

}
