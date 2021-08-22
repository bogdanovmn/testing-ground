package com.github.bogdanovmn.testingground.springboot.infrastructure.config;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
class ExceptionResponse {
	String message;
	int code;
	String exception;
	List<String> stacktrace;

}
