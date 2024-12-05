package com.danal.batch.job.normalRestaurant;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NormalRestaurantWriterTest {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final ObjectMapper objectMapper = new ObjectMapper()
		.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	@Test
	@DisplayName("normalRestaurantWhiter 테스트")
	void normalRestaurantWhiter() throws Exception {
		NormalRestaurantReader reader = new NormalRestaurantReader();
		reader.setResource(new FileSystemResource("src/test/resources/static/fulldata_07_24_04_P_일반음식점_test.csv"));

		reader.open(new ExecutionContext());
		NormalRestaurantDTO row = reader.read();
		List<NormalRestaurantDTO> readDataList = new ArrayList<>();
		while (row != null) {
			readDataList.add(row);
			row = reader.read();
		}
		reader.close();

		NormalRestaurantWriter writer = new NormalRestaurantWriter(dataSource);
		writer.afterPropertiesSet();
		writer.write(new Chunk<>(readDataList));
		List<NormalRestaurantDTO> selectDataList = jdbcTemplate
			.queryForList("SELECT * FROM normal_restaurant ORDER BY number ASC")
			.stream()
			.map(d -> objectMapper.convertValue(d, NormalRestaurantDTO.class))
			.toList();

		assertThat(readDataList.size()).isEqualTo(selectDataList.size());
		for (int i = 0; i < readDataList.size(); i++) {
			assertThat(readDataList.get(i)).isEqualTo(selectDataList.get(i));
		}
	}
  
}