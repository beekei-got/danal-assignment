package com.danal.batch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@SpringBatchTest
@EnableBatchProcessing
@ComponentScan(basePackages = "com.danal.batch")
public class BatchTestConfig {

	@Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
		return new JobLauncherTestUtils();
    }

}
