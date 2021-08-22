package com.github.bogdanovmn.testingground.springboot.something;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class SomethingService {
	private final SomethingRepository somethingRepository;

	Something get(long id) {
		return somethingRepository.getById(id);
	}

	@Transactional
	public Something create(SomethingPostRequest postRequest) throws Exception {
		Something x = somethingRepository.save(
			new Something()
				.setTitle(postRequest.getTitle())
		);

		if (x.id == 2) {
			throw new Exception("Boom!");
		}
		return x;
	}

	 Page<Something> getPage(int page, int size) {
		return somethingRepository.findAll(
			PageRequest.of(
				page - 1,
				size,
				Sort.by(
					Sort.Order.asc("id")
				)
			)
		);
	}
}
