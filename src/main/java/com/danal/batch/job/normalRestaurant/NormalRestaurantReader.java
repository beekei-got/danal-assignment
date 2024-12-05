package com.danal.batch.job.normalRestaurant;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class NormalRestaurantReader extends FlatFileItemReader<NormalRestaurantDTO> {

	public NormalRestaurantReader(@Value("${spring.batch.file-path}") String filePath) {
		DefaultLineMapper<NormalRestaurantDTO> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
		lineMapper.setFieldSetMapper(new NormalRestaurantDTOMapper());

		this.setName("normalRestaurantReader");
		this.setEncoding("euc-kr");
		this.setLineMapper(lineMapper);
		this.setLinesToSkip(1);
		this.setResource(new FileSystemResource(filePath));
	}

}
