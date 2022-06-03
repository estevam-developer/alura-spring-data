package br.com.estevam.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.estevam.spring.data.entities.Cargo;
import br.com.estevam.spring.data.repositories.CargoRepository;

@Service
public class CrudCargoService {

	private final CargoRepository cargoRepository;

	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
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
					listar();
					break;
			}
			
			if (sair) {
				break;
			}		
		}
	}
	
	private void salvar(Scanner scanner) {
		System.out.print("\nInforme a Descrição do Cargo: ");
		
		cargoRepository.save(new Cargo(scanner.next()));
		
		System.out.println("\nCargo registrado!");
	}
	
	private void atualizar(Scanner scanner) {
		Cargo cargo = new Cargo();
		
		System.out.print("\nInforme o Id do cargo: ");
		cargo.setId(scanner.nextInt());

		System.out.print("\nInforme a Descrição do cargo: ");
		cargo.setDescricao(scanner.next());
		
		cargoRepository.save(cargo);

		System.out.println("\nCargo atualizado!");
	}
	
	private void excluir(Scanner scanner) {
		System.out.print("\nInforme o Id do cargo: ");
		cargoRepository.deleteById(scanner.nextInt());
		System.out.println("\nCargo excluído!");
	}

	private void listar() {
		cargoRepository.findAll().forEach(System.out::println);
	}
	
}
