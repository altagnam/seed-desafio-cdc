package br.com.jornada.dev.primeiro.desafio.model;

/**
 * @author rafael.altagnam
 *
 */
public class AutorResponse {

	private final Long id;
	private final String nome;
	
	
	/**
	 * @param id
	 * @param nome
	 */
	public AutorResponse(Long id, String nome) {
		super();
		this.id = id;
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
	
	
}
