package com.danal.batch.job.normalRestaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResource;

class NormalRestaurantDTOTest {

	@Test
	@DisplayName("FiledSet을 이용한 NormalRestaurantDTO 생성")
	void createOfFieldSet() {
		NormalRestaurantReader reader = new NormalRestaurantReader();
		reader.setResource(new FileSystemResource("src/main/resources/static/normal-restaurant-test.csv"));

//		NormalRestaurantDTO.createOfFieldSet()
	}

}