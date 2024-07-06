package br.com.alurafood.pagamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class PagamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagamentosApplication.class, args);
	}

}
