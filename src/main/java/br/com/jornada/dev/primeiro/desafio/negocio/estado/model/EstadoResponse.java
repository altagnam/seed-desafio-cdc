package br.com.jornada.dev.primeiro.desafio.negocio.estado.model;

import org.springframework.util.Assert;

import br.com.jornada.dev.primeiro.desafio.negocio.estado.EstadoEntidade;
import jakarta.validation.constraints.NotNull;

public class EstadoResponse {

	private Long id;
	private String nome;
	private PaisEstadoVinculado pais;
	
	/**
	 * @param id
	 * @param nome
	 * @param pais
	 */
	public EstadoResponse(@NotNull EstadoEntidade estado) {
		super();
		Assert.notNull(estado, "Estado deve ser informado");
		Assert.notNull(estado.getPais(), "País vinculado ao estado deve ser informado");
		this.id = estado.getId();
		this.nome = estado.getNome();
		this.pais = new PaisEstadoVinculado(estado.getPais().getNome());
	}
	
	public record PaisEstadoVinculado(String nome) {}

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
	 * @return the pais
	 */
	public PaisEstadoVinculado getPais() {
		return pais;
	}

	@Override
	public String toString() {
		return "EstadoResponse [id=" + id + ", nome=" + nome + ", pais=" + pais + "]";
	}
	
}
