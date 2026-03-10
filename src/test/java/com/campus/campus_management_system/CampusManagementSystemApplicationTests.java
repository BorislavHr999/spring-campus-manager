package com.campus.campus_management_system;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CampusManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
    void intentionalFailureTest() {
        int result = 2 + 2;
        Assertions.assertEquals(5, result, "Този тест умишлено гърми, за да тества CI/CD защитата!");
    }
}