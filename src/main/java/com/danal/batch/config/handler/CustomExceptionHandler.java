package com.danal.batch.config.handler;

import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.exception.ExceptionHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomExceptionHandler implements ExceptionHandler {
	@Override
	public void handleException(RepeatContext context, Throwable throwable) {
		// TODO : 슬랙으로 알림을 보내는 로직 추가
		throw new RuntimeException(throwable);
	}
}
