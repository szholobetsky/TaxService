package com.example.tax.taxservice;

import com.example.tax.taxservice.controller.TaxRestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaxServiceApplicationTests {

	@Autowired
	TaxRestController taxRestController;
	@Test
	void contextLoads() {
		Assertions.assertNotNull(taxRestController);
	}

}
