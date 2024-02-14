package com.example.examination2;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.examination2.presentation.controller.BookRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Examination2ApplicationTests {

	@Autowired
	private BookRestController bookRestController;

	@Test
	void contextLoads() {
		assertThat(bookRestController).isNotNull();
	}

}
