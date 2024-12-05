package com.danal.batch.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Component
public class CustomChunkListener implements ChunkListener {

	@Override
	public void beforeChunk(ChunkContext context) {
		context.setAttribute("startDate", LocalDateTime.now());
//		StepExecution stepExecution = context.getStepContext().getStepExecution();
//		log.debug(">>>>>    [Chunk start]   readCount : {} | writeCount : {} | writeSkipCount : {} | rollbackCount : {}",
//			stepExecution.getReadCount(),
//			stepExecution.getWriteCount(),
//			stepExecution.getWriteSkipCount(),
//			stepExecution.getRollbackCount());
	}

	@Override
	public void afterChunk(ChunkContext context) {
		StepExecution stepExecution = context.getStepContext().getStepExecution();
		LocalDateTime startTime = (LocalDateTime) context.getAttribute("startDate");
		LocalDateTime endTime = LocalDateTime.now();
		Duration diff = Duration.between(startTime, endTime);

		log.debug("<<<<<    [Chunk finish]  readCount : {} | writeCount : {} | writeSkipCount : {} | rollbackCount : {} | runTime : {}",
			stepExecution.getReadCount(),
			stepExecution.getWriteCount(),
			stepExecution.getWriteSkipCount(),
			stepExecution.getRollbackCount(),
			diff.getSeconds() + "s");
	}

	@Override
	public void afterChunkError(ChunkContext context) {
		StepExecution stepExecution = context.getStepContext().getStepExecution();
		log.error("<<<<<    [Chunk error]   readCount : {} | writeCount : {} | writeSkipCount : {} | rollbackCount : {}",
			stepExecution.getReadCount(),
			stepExecution.getWriteCount(),
			stepExecution.getWriteSkipCount(),
			stepExecution.getRollbackCount());
	}

}
