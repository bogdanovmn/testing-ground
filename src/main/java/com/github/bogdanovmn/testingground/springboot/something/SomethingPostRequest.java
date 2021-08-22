package com.github.bogdanovmn.testingground.springboot.something;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class SomethingPostRequest {
	String title;
}
