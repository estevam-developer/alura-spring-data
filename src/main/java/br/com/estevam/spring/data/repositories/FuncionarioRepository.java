package br.com.estevam.spring.data.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.estevam.spring.data.entities.Funcionario;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {

}
