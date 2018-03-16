package br.edu.iff.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.iff.biblioteca.model.Editora;

public interface Editoras extends JpaRepository<Editora, Long> {
	
	@Query(value ="SELECT * "
			+ " FROM editora a "
			+ " ORDER BY a.id_editora", nativeQuery = true)
	List<Editora> findAllOrderByEscritor();

}
