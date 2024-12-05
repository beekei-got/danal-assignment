package com.danal.batch.job.normalRestaurant;

import com.danal.batch.config.BatchTestConfig;
import com.danal.batch.config.H2Config;
import com.danal.batch.config.NormalRestaurantBatchConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { H2Config.class, BatchTestConfig.class, NormalRestaurantBatchConfig.class })
class NormalRestaurantBatchJobTest {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private Job normalRestaurantBatchJob;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final ObjectMapper objectMapper = new ObjectMapper()
		.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
		.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);


	@Test
	@DisplayName("normalRestaurantBatchJob")
	public void normalRestaurantBatchJob() throws Exception {
		jobLauncherTestUtils.setJobLauncher(jobLauncher);
		jobLauncherTestUtils.setJob(normalRestaurantBatchJob);
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);

		List<NormalRestaurantDTO> selectDataList = jdbcTemplate
			.queryForList("SELECT * FROM normal_restaurant ORDER BY number ASC")
			.stream()
			.map(d -> objectMapper.convertValue(d, NormalRestaurantDTO.class))
			.toList();

		assertThat(selectDataList.size()).isEqualTo(100);
		for (int i = 0; i < selectDataList.size(); i++) {
			assertThat(selectDataList.get(i).getNumber()).isEqualTo(i + 1);
		}
	}

}