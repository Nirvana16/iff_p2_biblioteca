package br.edu.iff.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.iff.biblioteca.model.Escritor;

public interface Escritores extends JpaRepository<Escritor, Long> {
	
	@Query(value ="SELECT * "
			+ " FROM escritor a "
			+ " ORDER BY a.id_escritor", nativeQuery = true)
	List<Escritor> findAllOrderByEscritor();
	

}