package com.danal.batch.job.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomItemWriteListener<T> implements ItemWriteListener<T> {

	@Override
	public void afterWrite(Chunk<? extends T> items) {
		for (T item : items.getItems()) {
			log.debug("<<< [Item write] {}", item);
		}
	}

	@Override
	public void onWriteError(Exception exception, Chunk<? extends T> items) {
		log.error("[Item write error] [{}] {}", exception.getClass().getSimpleName(), exception.getMessage());
	}

}
