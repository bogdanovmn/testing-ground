package com.github.bogdanovmn.testingground.springboot.something;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class SomethingResponse {
	Long id;

	String title;

	static SomethingResponse of(Something something) {
		return SomethingResponse.builder()
			.id(something.getId())
			.title(something.getTitle())
		.build();
	}
}
