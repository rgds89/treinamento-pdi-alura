package br.com.alura.adopet;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@SpringBootConfiguration
@TestPropertySource(properties = "spring.config.location=classpath:/application-test.yaml")
class AdopetApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
