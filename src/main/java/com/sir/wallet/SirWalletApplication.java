package com.sir.wallet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class SirWalletApplication {

	private static final Logger logger = LogManager.getLogger(SirWalletApplication.class);

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		logger.info("Starting application");
		SpringApplication.run(SirWalletApplication.class, args);
	}

}
