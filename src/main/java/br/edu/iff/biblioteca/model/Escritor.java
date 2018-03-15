package br.edu.iff.biblioteca.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Escritor {
	
	@Id
	@GeneratedValue
	@Column(name="id_escritor")
	private Long idEscritor;

	@NotEmpty(message="O nome é obrigatório!")
	private String nome;
	
	@NotEmpty(message="A Nacionalidade é obrigatória")
	private String nacionalidade;
	
	@NotNull(message = "Data de Nascimento é obrigatória")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE )
	private Date dtNascimento;

	
	@OneToMany(mappedBy = "escritor", cascade = CascadeType.ALL)
	private Set<Livro> livros;


	public Long getIdEscritor() {
		return idEscritor;
	}
	public void setIdEscritor(Long idEscritor) {
		this.idEscritor = idEscritor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public Date getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public Set<Livro> getLivros() {
		return livros;
	}
	public void setLivros(Set<Livro> livros) {
		this.livros = livros;
	}
}
