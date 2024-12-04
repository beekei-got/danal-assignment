package com.danal.batch.job.normalRestaurant;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class NormalRestaurantReader extends FlatFileItemReader<NormalRestaurantDTO> {

	public NormalRestaurantReader() {
		DefaultLineMapper<NormalRestaurantDTO> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
		lineMapper.setFieldSetMapper(new NormalRestaurantDTOMapper());

		this.setName("normalRestaurantReader");
		this.setResource(new FileSystemResource("src/main/resources/static/fulldata_07_24_04_P_일반음식점.csv"));
		this.setEncoding("euc-kr");
		this.setLineMapper(lineMapper);
		this.setLinesToSkip(1);
	}

}
