package com.danal.batch.job.normalRestaurant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;

@ToString
@Getter
@NoArgsConstructor
public class NormalRestaurantDTO {

	private Integer number;
	private String service_name;
	private String service_id;
	private String government_code;
	private String management_number;
	private String permission_date;
	private String permission_cancel_date;
	private String open_status_code;
	private String open_status_name;
	private String detail_open_status_code;
	private String detail_open_status_name;
	private String closed_date;
	private String closing_start_date;
	private String closing_end_date;
	private String re_open_date;
	private String location_tel;
	private Double location_area_size;
	private String location_zip_code;
	private String location_address;
	private String road_address;
	private String road_zip_code;
	private String business_name;
	private String last_updated_date;
	private String data_updated_type;
	private String data_updated_date;
	private String business_type;
	private Double coordinate_x;
	private Double coordinate_y;
	private String hygiene_type;
	private Integer male_worker_count;
	private Integer female_worker_count;
	private String circumference_type;
	private String grade_type;
	private String water_supply_type;
	private Integer total_worker_count;
	private Integer office_worker_count;
	private Integer factory_desk_worker_count;
	private Integer factory_sales_worker_count;
	private Integer factory_producing_worker_count;
	private String building_own_type;
	private BigDecimal guaranteed_amount;
	private BigDecimal monthly_rent_amount;
	private String is_multiple_used;
	private Double facility_size;
	private String traditional_number;
	private String traditional_food;
	private String homepage;

	public static String[] getColumnNames() {
		return Arrays.stream(NormalRestaurantDTO.class.getDeclaredFields())
			.map(Field::getName)
			.toArray(String[]::new);
	}

	public Object[] getColumnValues() {
		return Arrays.stream(this.getClass().getDeclaredFields())
			.map(field -> {
				field.setAccessible(true);
				try {
					return field.get(this);
				} catch (IllegalAccessException e) {
					return null;
				}
			})
			.toArray(Object[]::new);
	}

}