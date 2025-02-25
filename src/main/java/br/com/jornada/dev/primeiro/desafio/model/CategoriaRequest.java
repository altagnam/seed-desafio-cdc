package br.com.jornada.dev.primeiro.desafio.model;

import org.springframework.util.Assert;

import br.com.jornada.dev.primeiro.desafio.entidade.CategoriaEntidade;
import jakarta.validation.constraints.NotBlank;

/**
 * <p>Classe de saída, quando o cadastro de uma categoria for realizado. </p>
 * 
 * @author rafael.altagnam
 * @desafio 1
 */
public class CategoriaRequest {

	@NotBlank(message = "Nome é obrigatório")
	private final String nome;
	

	/**
	 * @param nome
	 */
	public CategoriaRequest(final String nome) {
		this.nome = nome;
	}


	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	
	/**
	 * Cria uma instancia que representa o mapeamento da classe no banco de dados
	 * @return
	 */
	public CategoriaEntidade toEntidade() {
		Assert.hasText(nome, "Nome é obrigatório");
		return new CategoriaEntidade(getNome()); 
	}


	@Override
	public String toString() {
		return "CategoriaRequest [nome=" + nome + "]";
	}

}
