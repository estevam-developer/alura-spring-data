package br.com.estevam.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.estevam.spring.data.repositories.FuncionarioRepository;

@Service
public class RelatorioService {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatorioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		
		boolean sair = false;
		
		while (true) {
			System.out.println("\nSelecione uma opção:\n");
			System.out.println("0 - Sair");
			System.out.println("1 - Consulta funcionário por nome");
			System.out.println("2 - Consulta funcionário por nome, salário maior e data de contratação");
			
			switch (scanner.next()) {
				case "0":
					sair = true;
					break;
				case "1":
					consultarFuncionarioPorNome(scanner);
					break;
				case "2":
					consultarFuncionarioPorNomeSalarioMaiorDataContratacao(scanner);
					break;
			}
			
			if (sair) {
				break;
			}		
		}				
	}

	private void consultarFuncionarioPorNome(Scanner scanner) {
		System.out.print("\nInforme o Nome do Funcionário: ");
		funcionarioRepository.findByNome(scanner.next()).forEach(System.out::println);
	}
	
	private void consultarFuncionarioPorNomeSalarioMaiorDataContratacao(Scanner scanner) {
		System.out.print("\nInforme o Nome do Funcionário: ");
		String nome = scanner.next();
		
		System.out.print("\nInforme o Salário do Funcionário: ");
		BigDecimal salario = new BigDecimal(scanner.next());
		
		System.out.print("\nInforme o Data de Contratação do Funcionário: ");
		LocalDate dataContratacao = LocalDate.parse(scanner.next(), FORMATTER);
		
		funcionarioRepository.findByNomeAndSalarioMaiorAndDataContratacao(nome, salario, dataContratacao).forEach(System.out::print);
	}
}
