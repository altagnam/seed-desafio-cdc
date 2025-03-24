package br.com.jornada.dev.primeiro.desafio.entidade;

import org.springframework.util.Assert;

import br.com.jornada.dev.primeiro.desafio.model.CategoriaResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
@jakarta.persistence.Table(name = "CATEGORIA")
public class CategoriaEntidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "NOME", nullable = false)
	private String nome;
	
	/**
	 * Construtor utilizado somente pelo JPA
	 */
	@Deprecated
	protected CategoriaEntidade() {
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * @param nome
	 */
	public CategoriaEntidade(@NotNull String nome) {
		super();
		Assert.hasText(nome, "Nome é obrigatório");
		this.nome = nome;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * Cria uma instancia de resposta da classe registrada na base de dados
	 * @return
	 */
	public CategoriaResponse toCategoriaResponse() {
		return new CategoriaResponse(getId(), getNome());
	}

	@Override
	public String toString() {
		return "CategoriaEntidade [id=" + id + ", nome=" + nome + "]";
	}

	

}
