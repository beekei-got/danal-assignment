package com.danal.batch.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Component
public class CustomStepExecutionListener extends StepExecutionListenerSupport {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		log.debug(">>>>>    [Step start]    name: {}", stepExecution.getStepName());
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		LocalDateTime startTime = stepExecution.getStartTime();
		LocalDateTime endTime = stepExecution.getEndTime();
		Duration diff = Duration.between(startTime, endTime);
		log.debug("<<<<<    [Step finish]   name: {} | status : {} | runTime : {}",
			stepExecution.getStepName(),
			stepExecution.getStatus(),
			diff.getSeconds() + "s");
		return stepExecution.getExitStatus();
	}

}
