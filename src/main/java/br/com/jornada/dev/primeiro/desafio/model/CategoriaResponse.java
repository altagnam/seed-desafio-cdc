package br.com.jornada.dev.primeiro.desafio.model;

/**
 * <p>Classe de entrada para realizar o cadastro de uma Categoria. </p>
 * <p>Toda categoria precisa de um nome, onde o nome é obrigatório e não pode ser duplicado.</p>
 * 
 * @author rafael.altagnam
 * @desafio 1
 */
public class CategoriaResponse {

	private final Long id;
	private final String nome;

	/**
	 * @param nome
	 */
	public CategoriaResponse(final  Long id, final String nome) {
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
		return "CategoriaResponse [id=" + id + ", nome=" + nome + "]";
	}

	
}
