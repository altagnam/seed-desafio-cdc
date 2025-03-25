package br.com.jornada.dev.primeiro.desafio.model;

/**
 * <p>
 * Classe de entrada para realizar o cadastro de um Livor.
 * </p>
 * <b>Necessidades:</b><br/>
 * <ul>
 * Um título
 * <li>Um resumo do que vai ser encontrado no livro</li>
 * <li>Um sumário de tamanho livre. O texto deve entrar no formato markdown, que
 * é uma string. Dessa forma ele pode ser formatado depois da maneira
 * apropriada.</li>
 * <li>Preço do livro</li>
 * <li>Número de páginas</li>
 * <li>Isbn(identificador do livro)</li>
 * <li>Data que ele deve entrar no ar(de publicação)</li>
 * <li>Um livro pertence a uma categoria</li>
 * <li>Um livro é de um autor</li>
 * 
 * <ul>
 * 
 * @author rafael.altagnam
 * @desafio 1
 */
public class LivroResponse {
	
	private Long id;

	private String titulo;
	

	/**
	 * @param id
	 * @param titulo
	 * @param preco
	 * @param numeroPaginas
	 * @param isbn
	 * @param dataPublicacao
	 */
	public LivroResponse(Long id, String titulo) {
		super();
		this.id = id;
		this.titulo = titulo;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}


	
}
