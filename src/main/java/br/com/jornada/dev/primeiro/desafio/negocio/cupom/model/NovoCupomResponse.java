package br.com.jornada.dev.primeiro.desafio.negocio.cupom.model;

import br.com.jornada.dev.primeiro.desafio.negocio.cupom.CupomEntidade;

/**
 * 
 * @author rafael.altagnam
 *
 */
public class NovoCupomResponse {

	private final Long id;
	private final String codigo;
	
	public NovoCupomResponse(CupomEntidade cupomCadastrado) {
		this.id = cupomCadastrado.getId();
		this.codigo = cupomCadastrado.getCodigo();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	@Override
	public String toString() {
		return "NovoCupomResponse [id=" + id + ", codigo=" + codigo + "]";
	}
	
	
}
