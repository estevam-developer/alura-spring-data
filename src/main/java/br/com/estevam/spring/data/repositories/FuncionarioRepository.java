package br.com.estevam.spring.data.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.estevam.spring.data.entities.Funcionario;

public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer> {

	Iterable<Funcionario> findByNome(String nome);
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :dataContratacao")
	Iterable<Funcionario> findByNomeAndSalarioMaiorAndDataContratacao(String nome,BigDecimal salario, LocalDate dataContratacao);

}
