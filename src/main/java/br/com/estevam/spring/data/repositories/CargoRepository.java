package br.com.estevam.spring.data.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.estevam.spring.data.entities.Cargo;

public interface CargoRepository extends CrudRepository<Cargo, Integer> {

}
