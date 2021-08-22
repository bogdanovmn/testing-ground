package com.github.bogdanovmn.testingground.springboot.something;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter

@Entity
class Something {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Access(AccessType.PROPERTY)
	Long id;

	String title;
}
