package com.danal.batch.config;

import com.danal.batch.job.normalRestaurant.NormalRestaurantDTO;
import com.danal.batch.job.normalRestaurant.NormalRestaurantReader;
import com.danal.batch.job.normalRestaurant.NormalRestaurantWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.exception.ExceptionHandler;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class NormalRestaurantBatchConfig {

	private final JobRepository jobRepository;
	private final NormalRestaurantReader normalRestaurantReader;
	private final NormalRestaurantWriter normalRestaurantWriter;
	private final JobExecutionListener customJobExecutionListener;
	private final StepExecutionListener customStepExecutionListener;
	private final ChunkListener customChunkListener;
	private final ExceptionHandler customExceptionHandler;

	private static final int CHUNK_SIZE = 10000;

	@Bean(name = "normalRestaurantBatchJob")
	public Job normalRestaurantBatchJob() {
		return new JobBuilder("NormalRestaurantBatchJob", jobRepository)
			.incrementer(new RunIdIncrementer())
			.start(normalRestaurantBatchStep())
			.listener(customJobExecutionListener)
			.build();
	}

	@Bean(name = "normalRestaurantBatchStep")
	public Step normalRestaurantBatchStep() {
		return new StepBuilder("NormalRestaurantBatchStep", jobRepository)
			.<NormalRestaurantDTO, NormalRestaurantDTO>chunk(CHUNK_SIZE, new ResourcelessTransactionManager())
			.reader(normalRestaurantReader)
			.writer(normalRestaurantWriter)
			.listener(customStepExecutionListener)
			.listener(customChunkListener)
			.faultTolerant()
			.exceptionHandler(customExceptionHandler)
			.build();
	}

}
