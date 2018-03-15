package br.edu.iff.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iff.biblioteca.model.Livro;

public interface Livros extends JpaRepository<Livro, Long> {

}
