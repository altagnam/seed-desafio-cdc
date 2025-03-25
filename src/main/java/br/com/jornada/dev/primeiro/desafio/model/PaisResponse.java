package br.com.jornada.dev.primeiro.desafio.model;

/**
 * <p>Classe que representa os dados que serão enviados ao cliente apos o cadastro de um Pais.</p>
 * 
 * @author rafael.altagnam
 * @desafio 1
 */
public class PaisResponse {
	
	private Long id;
	private String nome;

	/**
	 * @param nome
	 */
	public PaisResponse(final Long id, final String nome) {
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

	@Override
	public String toString() {
		return "PaisResponse [nome=" + nome + "]";
	}

}
