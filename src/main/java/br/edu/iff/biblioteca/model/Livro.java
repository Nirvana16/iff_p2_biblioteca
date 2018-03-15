package br.edu.iff.biblioteca.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Livro {
	
	@Id
	@GeneratedValue
	@Column(name="id_livro")
	private Long idLivro;
	@NotNull(message = "Escritor Inválido")
	@ManyToOne
	@JoinColumn(name = "id_escritor")
	private Escritor escritor;
	
	@NotEmpty(message="livro Inválido")
	private String nomeL;
	
	@NotNull(message = "Data Inválida")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE )
	private Date dataL;
	
	@NotNull(message = "O valor Inválido")
	@DecimalMin(value = "0.01", message = "Valor não pode ser menor que 0,01")
	private Float valor;
	
	public Long getIdLivro() {
		return idLivro;
	}
	public void setIdLivro(Long idLivro) {
		this.idLivro = idLivro;
	}
	public Escritor getEscritor() {
		return escritor;
	}
	public void setEscritor(Escritor escritor) {
		this.escritor = escritor;
	}
	public String getNomeL() {
		return nomeL;
	}
	public void setNomeL(String nomeL) {
		this.nomeL = nomeL;
	}
	public Date getDataL() {
		return dataL;
	}
	public void setDataL(Date dataL) {
		this.dataL = dataL;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
}
