package br.com.estevam.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.estevam.spring.data.entities.Funcionario;
import br.com.estevam.spring.data.entities.UnidadeTrabalho;
import br.com.estevam.spring.data.repositories.CargoRepository;
import br.com.estevam.spring.data.repositories.FuncionarioRepository;
import br.com.estevam.spring.data.repositories.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private final FuncionarioRepository funcionarioRepository;
	private final CargoRepository cargoRepository;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository,
			CargoRepository cargoRepository,
			UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}

	public void inicial(Scanner scanner) {
		
		boolean sair = false;
		
		while (true) {
			System.out.println("\nSelecione uma opção:\n");
			
			System.out.println("0 - Sair");
			System.out.println("1 - Incluir");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Excluir");
			System.out.println("4 - Listar");
			
			switch (scanner.next()) {
				case "0":
					sair = true;
					break;
				case "1":
					salvar(scanner);
					break;
				case "2":
					atualizar(scanner);
					break;
				case "3":
					excluir(scanner);
					break;
				case "4":
					listar(scanner);
					break;
			}
			
			if (sair) {
				break;
			}
		}

	}

	private void salvar(Scanner scanner) {
		
		Funcionario funcionario = new Funcionario();
		
		System.out.print("\nInforme o Nome do Funcionário:");
		funcionario.setNome(scanner.next());
		
		System.out.print("\nInforme o CPF do Funcionário:");
		funcionario.setCpf(scanner.next());

		System.out.print("\nInforme o Salário do Funcionário:");
		funcionario.setSalario(new BigDecimal(scanner.next()));

		System.out.print("\nInforme a Data de Contratação do Funcionário:");
		funcionario.setDataContratacao(LocalDate.parse(scanner.next(), FORMATTER));
		
		System.out.print("\nInforme o Id do Cargo do Funcionário:");
		funcionario.setCargo(cargoRepository.findById(scanner.nextInt()).get());
		
		funcionario.setUnidadesTrabalho(informarUnidadesTrabalho(scanner));
		
		funcionarioRepository.save(funcionario);
		
		System.out.println("\nFuncionario registrado!");	
	}

	private void atualizar(Scanner scanner) {

		Funcionario funcionario = new Funcionario();
		
		System.out.print("\nInforme o Id do Funcionário:");
		funcionario.setId(scanner.nextInt());
		
		System.out.print("\nInforme o Nome do Funcionário:");
		funcionario.setNome(scanner.next());
		
		System.out.print("\nInforme o CPF do Funcionário:");
		funcionario.setCpf(scanner.next());

		System.out.print("\nInforme o Salário do Funcionário:");
		funcionario.setSalario(new BigDecimal(scanner.next()));

		System.out.print("\nInforme a Data de Contratação do Funcionário:");
		funcionario.setDataContratacao(LocalDate.parse(scanner.next(), FORMATTER));
		
		funcionarioRepository.save(funcionario);
		
		System.out.println("\nFuncionario atualizado!");	
	}

	private void excluir(Scanner scanner) {
		System.out.print("\nInforme o Id do Funcionário:");
		funcionarioRepository.deleteById(scanner.nextInt());
		System.out.println("\nFuncionário excluído!");			
	}

	private void listar(Scanner scanner) {
		funcionarioRepository.findAll().forEach(System.out::println);
	}

	private List<UnidadeTrabalho> informarUnidadesTrabalho(Scanner scanner) {
		
		Integer id;
		List<Integer> ids = new ArrayList<>();
		
		while (true) {
			System.out.print("\nInforme o Id da Unidade (0 para nenhuma):");
			id = scanner.nextInt();
		
			if (id == 0) {
				break;
			}
			
			ids.add(id);
			
		}

		List<UnidadeTrabalho> resultado = new ArrayList<>();
		
		unidadeTrabalhoRepository.findAllById(ids).forEach(resultado::add);

		return resultado;
	}
}
