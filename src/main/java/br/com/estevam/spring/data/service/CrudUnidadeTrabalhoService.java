package br.com.estevam.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.estevam.spring.data.entities.UnidadeTrabalho;
import br.com.estevam.spring.data.repositories.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
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
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		
		System.out.print("\nInforme a Descrição da Unidade de Trabalho:");
		unidadeTrabalho.setDescricao(scanner.next());

		System.out.print("\nInforme Endereço da Unidade de Trabalho:");
		unidadeTrabalho.setEndereco(scanner.next());

		unidadeTrabalhoRepository.save(unidadeTrabalho);
		
		System.out.println("\nUnidade de Trabalho registrada!");
	}

	private void atualizar(Scanner scanner) {
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		
		System.out.print("\nInforme o Id da Unidade de Trabalho:");
		unidadeTrabalho.setId(scanner.nextInt());
		
		System.out.print("\nInforme a Descrição da Unidade de Trabalho:");
		unidadeTrabalho.setDescricao(scanner.next());
		
		System.out.print("\nInforme Endereço da Unidade de Trabalho:");
		unidadeTrabalho.setEndereco(scanner.next());

		unidadeTrabalhoRepository.save(unidadeTrabalho);
		
		System.out.println("\nUnidade de Trabalho atualizada!");
	}

	private void excluir(Scanner scanner) {
		System.out.print("\nInforme o Id da Unidade de Trabalho:");
		unidadeTrabalhoRepository.deleteById(scanner.nextInt());
		System.out.println("\nUnidade de Trabalho excluída!");	
	}
	
	private void listar(Scanner scanner) {
		unidadeTrabalhoRepository.findAll().forEach(System.out::println);
	}

}
