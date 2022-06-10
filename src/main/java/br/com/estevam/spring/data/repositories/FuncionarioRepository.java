package br.com.estevam.spring.data.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.estevam.spring.data.entities.Funcionario;
import br.com.estevam.spring.data.entities.FuncionarioProjecao;

public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {

	Iterable<Funcionario> findByNome(String nome);
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :dataContratacao")
	Iterable<Funcionario> findByNomeAndSalarioMaiorAndDataContratacao(String nome,BigDecimal salario, LocalDate dataContratacao);
	
	@Query("SELECT f FROM Funcionario f")
	Iterable<FuncionarioProjecao> findByFuncionarioSalario(Sort sort);

}
