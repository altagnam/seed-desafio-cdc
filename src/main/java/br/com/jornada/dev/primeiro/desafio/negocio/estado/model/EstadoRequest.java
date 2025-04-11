package br.com.jornada.dev.primeiro.desafio.negocio.estado.model;

import br.com.jornada.dev.primeiro.desafio.negocio.estado.EstadoEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.pais.PaisEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.pais.repository.PaisRepositorio;
import br.com.jornada.dev.primeiro.desafio.validador.ExistisId;
import br.com.jornada.dev.primeiro.desafio.validador.UniqueValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * <p>Classe que representa os dados recebidos através de uma requisição para o
 * cadastro de um Estado.</p>
 * 
 * <b>restrições</b><br/>
 * o nome é obrigatório<br/>
 * o nome é único<br/>
 * o país é obrigatório <br/>
 * 
 * @author rafael.altagnam
 * @desafio 1
 */
public class EstadoRequest {
	
	@NotBlank
	@UniqueValue(fieldName = "nome", domainClass = EstadoEntidade.class)
	private String nome;
	
	@NotNull
	@ExistisId(fieldName = "id", domainClass = PaisEntidade.class)
	private Long pais;

	/**
	 * @param nome
	 * @param pais
	 */
	public EstadoRequest(@NotBlank String nome, @NotNull Long pais) {
		super();
		this.nome = nome;
		this.pais = pais;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the pais
	 */
	public Long getPais() {
		return pais;
	}
	
	
	/**
	 * Cria uma instancia de {@link EstadoEntidade}
	 * @param paisRepositorio
	 * @return
	 */
	public EstadoEntidade toModel(final PaisRepositorio paisRepositorio) {
		return new EstadoEntidade(
				getNome(),
				paisRepositorio.findById(pais).orElseThrow(() -> new IllegalArgumentException("País inválido."))
		);
	}

	@Override
	public String toString() {
		return "EstadoRequest [nome=" + nome + ", pais=" + pais + "]";
	}
	
}
