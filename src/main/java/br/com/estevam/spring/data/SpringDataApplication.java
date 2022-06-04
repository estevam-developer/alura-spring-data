package br.com.estevam.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.estevam.spring.data.service.CrudCargoService;
import br.com.estevam.spring.data.service.CrudFuncionarioService;
import br.com.estevam.spring.data.service.CrudUnidadeTrabalhoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private final CrudCargoService cargoService;
	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
	private final CrudFuncionarioService funcionarioService;
	
	public SpringDataApplication(CrudCargoService cargoService, CrudUnidadeTrabalhoService unidadeTrabalhoService,
			CrudFuncionarioService funcionarioService) {
		this.cargoService = cargoService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
		this.funcionarioService = funcionarioService;
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
			System.out.println("2 - Unidade de Trabalho");
			System.out.println("3 - Funcionário");
			
			switch (scanner.next()) {
				case "0":
					sair = true;
					break;
				case "1":
					cargoService.inicial(scanner);
					break;
				case "2":
					unidadeTrabalhoService.inicial(scanner);
					break;
				case "3":
					funcionarioService.inicial(scanner);
					break;
			}
			
			if (sair) {
				break;
			}
		}

	}

}
