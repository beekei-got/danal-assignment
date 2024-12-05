package com.danal.batch.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomItemReadListener<T> implements ItemReadListener<T> {

	@Override
	public void afterRead(T item) {
		log.debug(">>> [Item read] {}", item);
	}

	@Override
	public void onReadError(Exception exception) {
		log.error("[Item read error] [{}] {}", exception.getClass().getSimpleName(), exception.getMessage());
	}

}
