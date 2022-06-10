package br.com.estevam.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.estevam.spring.data.entities.Funcionario;
import br.com.estevam.spring.data.entities.FuncionarioProjecao;
import br.com.estevam.spring.data.repositories.FuncionarioRepository;
import br.com.estevam.spring.data.specification.SpecificationFuncionario;

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
			System.out.println("3 - Consulta funcionário salário");
			System.out.println("4 - Consulta funcionário dinâmica");
			
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
				case "3":
					consultarFuncionarioSalario();
					break;
				case "4":
					consultarFuncionarioDinamica(scanner);
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
	
	private void consultarFuncionarioSalario() {
		Iterable<FuncionarioProjecao> listaFuncionarios = funcionarioRepository.findByFuncionarioSalario(Sort.by("nome"));
		
		listaFuncionarios.forEach(f -> System.out.println("Funcionario = Id: " + f.getId() + "|Nome: " + f.getNome() + "|Salário: " + f.getSalario()));
	}

	private void consultarFuncionarioDinamica(Scanner scanner) {

		System.out.print("\nInforme o Nome do Funcionário (\"N\" para nenhum): ");
		String nome = scanner.next();
		
		if ("N".equalsIgnoreCase(nome) ) {
			nome = null;
		}

		System.out.print("\nInforme o Cpf do Funcionário (\"N\" para nenhum): ");
		String cpf = scanner.next();

		if ("N".equalsIgnoreCase(cpf) ) {
			cpf = null;
		}

		System.out.print("\nInforme o Salário do Funcionário (0 para nenhum): ");
		BigDecimal salario = new BigDecimal(scanner.next());
		
		if (salario.equals(BigDecimal.ZERO)) {
			salario = null;
		}
		
		System.out.print("\nInforme o Data de Contratação do Funcionário (\"N\" para nenhum): ");
		String data = scanner.next();
		LocalDate dataContratacao;
		
		if ("N".equalsIgnoreCase(data) ) {
			dataContratacao = null;
		} else {
			dataContratacao = LocalDate.parse(scanner.next(), FORMATTER);
		}
		
		Iterable<Funcionario> funcionarios = funcionarioRepository.findAll(
				 SpecificationFuncionario.comNomeContendo(nome)
				.and(SpecificationFuncionario.comCpf(cpf))
				.and(SpecificationFuncionario.comSalarioMaiorQue(salario))
				.and(SpecificationFuncionario.comDataContratacaoMaiorQue(dataContratacao)));

		funcionarios.forEach(System.out::println);
	}
}
