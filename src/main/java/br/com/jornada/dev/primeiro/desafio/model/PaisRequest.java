package br.com.jornada.dev.primeiro.desafio.model;

import org.springframework.util.Assert;

import br.com.jornada.dev.primeiro.desafio.entidade.PaisEntidade;
import br.com.jornada.dev.primeiro.desafio.validador.UniqueValue;
import jakarta.validation.constraints.NotBlank;

/**
 * <p>Classe que representa os dados recebidos através de uma requisição, para o
 * cadastro de um Pais.</p>
 * 
 * <b>restrições</b><br/>
 * o nome é obrigatório<br/>
 * o nome é único<br/>
 * 
 * @author rafael.altagnam
 * @desafio 1
 */
public class PaisRequest {
	
	@NotBlank
	@UniqueValue(fieldName = "nome", domainClass = PaisEntidade.class)
	private String nome;

	/**
	 * @param nome
	 */
	public PaisRequest(@NotBlank String nome) {
		super();
		this.nome = nome;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Cria uma instancia {@link PaisEntidade}
	 * @return
	 */
	public PaisEntidade toEntidade() {
		Assert.hasText(getNome(), "Nome não informado");
		return new PaisEntidade(getNome());
	}

	@Override
	public String toString() {
		return "PaisRequest [nome=" + nome + "]";
	}

}
