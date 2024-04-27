package com.adrianobarbosa.Api;


import com.adrianobarbosa.Api.principal.Principal;
import com.adrianobarbosa.Api.repository.CepRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

	private final CepRepository cepRepository;

	public ApiApplication(CepRepository cepRepository){
		this.cepRepository = cepRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(cepRepository);
		principal.exibeMenu();

	}
}
