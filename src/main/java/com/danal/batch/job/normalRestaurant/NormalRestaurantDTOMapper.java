package com.danal.batch.job.normalRestaurant;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.lang.reflect.Field;

public class NormalRestaurantDTOMapper implements FieldSetMapper<NormalRestaurantDTO> {

	@Override
	public NormalRestaurantDTO mapFieldSet(FieldSet fieldSet) {
		NormalRestaurantDTO dto = new NormalRestaurantDTO();
		Field[] fields = dto.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			String value = fieldSet.readString(i).trim();
			try {
				switch (field.getType().getSimpleName()) {
					case "String":
						field.set(dto, !value.isBlank() ? value : null);
						break;
					case "Integer":
						field.set(dto, !value.isBlank() ? fieldSet.readInt(i) : null);
						break;
					case "BigDecimal":
						field.set(dto, !value.isBlank() ? fieldSet.readBigDecimal(i) : null);
						break;
					default:
						throw new RuntimeException("Unsupported field type: " + field.getType());
				}
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		return dto;
	}

}
