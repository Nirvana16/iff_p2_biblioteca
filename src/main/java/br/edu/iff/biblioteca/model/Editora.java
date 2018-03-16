package br.edu.iff.biblioteca.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Editora {
	
	@Id
	@GeneratedValue
	@Column(name="id_editora")
	private Long idEditora;	

	@NotEmpty(message="Nome Inválido")
	private String nome;
	
	@NotNull(message = "Telefone Inválida")
	private String telefone;

	@NotNull(message = "CNPJ Inválido")
	private String cnpj;
	
	@NotNull(message = "Endereco Inválido")
	private String endereco;
	
	@OneToMany(mappedBy = "escritor", cascade = CascadeType.ALL)
	private Set<Livro> livros;
	
	
	public Set<Livro> getLivros() {
		return livros;
	}
	public void setLivros(Set<Livro> livros) {
		this.livros = livros;
	}
	public Long getIdEditora() {
		return idEditora;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public void setIdEditora(Long idEditora) {
		this.idEditora = idEditora;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
