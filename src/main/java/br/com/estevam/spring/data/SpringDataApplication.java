package br.com.estevam.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.estevam.spring.data.service.CrudCargoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private final CrudCargoService cargoService;
	
	public SpringDataApplication(CrudCargoService cargoService) {
		this.cargoService = cargoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Scanner scanner = new Scanner(System.in);
		
		boolean sair = false;
		
		while (true) {
			
			System.out.println("Selecione uma opção:\n");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			
			switch (scanner.next()) {
			case "0":
				sair = true;
				break;
			case "1":
				cargoService.inicial(scanner);
				break;
			}
			
			
			
			if (sair) {
				break;
			}
		}

	}

}
