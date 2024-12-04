package com.danal.batch.job.normalRestaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@StepScope
@Component
public class NormalRestaurantWriter extends JdbcBatchItemWriter<NormalRestaurantDTO> {

	public NormalRestaurantWriter(DataSource dataSource) {
		String sql = "INSERT INTO normal_restaurant" +
			"(" + String.join(", ", NormalRestaurantDTO.getColumnNames()) + ", created_datetime) " +
			"VALUES (:" + String.join(", :", NormalRestaurantDTO.getColumnNames()) + ", NOW())";
		this.setSql(sql);
		this.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		this.setDataSource(dataSource);
		this.afterPropertiesSet();
	}

}
