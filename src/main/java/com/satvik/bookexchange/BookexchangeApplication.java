package com.satvik.bookexchange;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@Slf4j
public class BookexchangeApplication {


	public static void main(String[] args) {

		SpringApplication.run(BookexchangeApplication.class, args);
		log.info("hello bois");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
