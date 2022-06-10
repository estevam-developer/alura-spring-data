package br.com.estevam.spring.data.specification;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.estevam.spring.data.entities.Funcionario;

public class SpecificationFuncionario {
	
	public static Specification<Funcionario> comNomeContendo(String nome) {
		return (root, criteriaQuery, criteriaBuilder) -> nome == null ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
	}

	public static Specification<Funcionario> comCpf(String cpf) {
		return (root, criteriaQuery, criteriaBuilder) -> cpf == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("cpf"), cpf);
	}
	
	public static Specification<Funcionario> comSalarioMaiorQue(BigDecimal salario) {
		return (root, criteriaQuery, criteriaBuilder) -> salario == null ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThan(root.get("salario"), salario);
	}
	
	public static Specification<Funcionario> comDataContratacaoMaiorQue(LocalDate dataContratacao) {
		return (root, criteriaQuery, criteriaBuilder) -> dataContratacao == null ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThan(root.get("dataContratacao"), dataContratacao);
	}
	
}
