package br.com.jornada.dev.primeiro.desafio.model;

import br.com.jornada.dev.primeiro.desafio.entidade.CategoriaEntidade;
import br.com.jornada.dev.primeiro.desafio.validador.UniqueValue;
import jakarta.validation.constraints.NotBlank;

/**
 * <p>Classe de saída, quando o cadastro de uma categoria for realizado. </p>
 * 
 * @author rafael.altagnam
 * @desafio 1
 */
public class CategoriaRequest {

	@NotBlank(message = "Nome é obrigatório")
	@UniqueValue(domainClass = CategoriaEntidade.class, fieldName = "nome")
	private final String nome;
	

	/**
	 * @param nome
	 */
	public CategoriaRequest(@NotBlank final String nome) {
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
		return new CategoriaEntidade(getNome()); 
	}


	@Override
	public String toString() {
		return "CategoriaRequest [nome=" + nome + "]";
	}

}
