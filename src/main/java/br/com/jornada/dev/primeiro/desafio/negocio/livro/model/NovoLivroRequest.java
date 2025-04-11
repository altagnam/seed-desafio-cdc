package br.com.jornada.dev.primeiro.desafio.negocio.livro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.jornada.dev.primeiro.desafio.negocio.autor.AutorEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.autor.repository.AutorRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.categoria.CategoriaEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.categoria.repository.CategoriaRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.livro.LivroEntidade;
import br.com.jornada.dev.primeiro.desafio.validador.ExistisId;
import br.com.jornada.dev.primeiro.desafio.validador.UniqueValue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
public class NovoLivroRequest {

	@NotBlank
	@UniqueValue(domainClass = LivroEntidade.class, fieldName = "titulo")
	private String titulo;

	@NotBlank
	@Length(max = 500)
	private String resumo;

	private String sumario;

	@NotNull
	@Min(20)
	private BigDecimal preco;

	@Min(100)
	private int numeroPaginas;

	@NotBlank
	@UniqueValue(domainClass = LivroEntidade.class, fieldName = "isbn")
	private String isbn;

	@Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataPublicacao;

	@NotNull
	@ExistisId(fieldName = "id", domainClass = CategoriaEntidade.class)
	private Long categoria;

	@NotNull
	@ExistisId(fieldName = "id", domainClass = AutorEntidade.class)
	private Long autor;

	/**
	 * @param titulo
	 * @param resumo
	 * @param sumario
	 * @param preco
	 * @param numeroPaginas
	 * @param isbn
	 * @param dataPublicacao
	 * @param categoria
	 * @param autor
	 */
	public NovoLivroRequest(@NotBlank String titulo, @NotBlank @Max(500) String resumo, String sumario,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) int numeroPaginas, @NotBlank String isbn,
			LocalDate dataPublicacao, @NotNull Long categoria, @NotNull Long autor) {
		super();
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
		this.categoria = categoria;
		this.autor = autor;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @return the resumo
	 */
	public String getResumo() {
		return resumo;
	}

	/**
	 * @return the sumario
	 */
	public Object getSumario() {
		return sumario;
	}

	/**
	 * @return the preco
	 */
	public BigDecimal getPreco() {
		return preco;
	}

	/**
	 * @return the numeroPaginas
	 */
	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @return the dataPublicacao
	 */
	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	/**
	 * @return the categoria
	 */
	public Long getCategoria() {
		return categoria;
	}

	/**
	 * @return the autor
	 */
	public Long getAutor() {
		return autor;
	}
	
	
	/**
	 * Instancia uma {@link LivroEntidade} de acordo com os dados recebidos na requisição enviada pelo usuário
	 * @param categoriaRepositorio
	 * @param autorRepositorio
	 * @return
	 */
	public LivroEntidade toEntidade(@NotNull final CategoriaRepositorio categoriaRepositorio, @NotNull final AutorRepositorio autorRepositorio) {
		LocalDate amanha = LocalDate.now().plusDays(1);
		var dataValida = dataPublicacao.isAfter(amanha) || dataPublicacao.isEqual(amanha);
		Assert.isTrue(dataValida, "Data de publicação deve ser uma data futura");
		
		return new LivroEntidade(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, 
				categoriaRepositorio.findById(categoria).orElseThrow(() -> new IllegalArgumentException("Categoria inválida.")), 
				autorRepositorio.findById(autor).orElseThrow(() -> new IllegalArgumentException("Autor inválido.")));
	}

	@Override
	public String toString() {
		return "LivroRequest [titulo=" + titulo + ", resumo=" + resumo + ", sumario=" + sumario + ", preco=" + preco
				+ ", numeroPaginas=" + numeroPaginas + ", isbn=" + isbn + ", dataPublicacao=" + dataPublicacao
				+ ", categoria=" + categoria + ", autor=" + autor + "]";
	}

}
