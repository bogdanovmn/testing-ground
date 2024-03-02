package com.github.bogdanovmn.testingground.springboot.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
class GlobalExceptionHandling {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ExceptionResponse> defaultError(HttpServletRequest req, Exception ex) throws Exception {
		if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
			throw ex;
		}
		return exceptionResponse(req, ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = MissingRequestValueException.class)
	public ResponseEntity<ExceptionResponse> badRequestException(HttpServletRequest req, Exception ex) throws Exception {
        return exceptionResponse(req, ex, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(value = JpaObjectRetrievalFailureException.class)
	public ResponseEntity<ExceptionResponse> notFoundException(HttpServletRequest req, Exception ex) throws Exception {
		return exceptionResponse(req, ex.getCause(), HttpStatus.NOT_FOUND);
	}

	private ResponseEntity<ExceptionResponse> exceptionResponse(HttpServletRequest req, Throwable ex, HttpStatus status) {
		LOG.error(
			String.format("%s %s processing error", req.getMethod(), req.getRequestURI()),
			ex
		);
		return ResponseEntity.status(status.value()).body(
			ExceptionResponse.builder()
				.message(ex.getMessage())
				.code(status.value())
				.exception(ex.getClass().getName())
				.stacktrace(
					Arrays.stream(
						ex.getStackTrace()
					).map(StackTraceElement::toString)
						.limit(10)
						.collect(Collectors.toList())
				)
				.build()
		);
	}
}
