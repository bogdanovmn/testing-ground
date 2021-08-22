package com.github.bogdanovmn.testingground.springboot.something;

import org.springframework.data.jpa.repository.JpaRepository;

interface SomethingRepository extends JpaRepository<Something, Long> {
}
