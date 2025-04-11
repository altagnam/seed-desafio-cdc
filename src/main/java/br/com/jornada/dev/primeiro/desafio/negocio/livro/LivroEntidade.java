package br.com.jornada.dev.primeiro.desafio.negocio.livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import br.com.jornada.dev.primeiro.desafio.negocio.autor.AutorEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.categoria.CategoriaEntidade;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * <p>
 * Classe para realizar a persistencia de um Livro.
 * </p>

 * 
 * @author rafael.altagnam
 * @desafio 1
 */
@Entity
@jakarta.persistence.Table(name = "LIVRO")
public class LivroEntidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "titulo", nullable = false, unique = true)
	private String titulo;

	@NotNull
	@Column(name = "resumo", nullable = false, length = 500)
	private String resumo;

	@Column(name = "sumario", nullable = true)
	private String sumario;

	@NotNull
	@Min(20)
	@Column(name = "preco", nullable = false)
	private BigDecimal preco;

	@NotNull
	@Min(100)
	@Column(name = "numero_paginas", nullable = false)
	private int numeroPaginas;

	@NotBlank
	@Column(name = "isbn", nullable = false)
	private String isbn;

	@Column(name = "data_publicacao")
	private LocalDate dataPublicacao;

	@JoinColumn(name = "id_categoria")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
	private CategoriaEntidade categoria;

	@JoinColumn(name = "id_autor")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
	private AutorEntidade autor;
	
	@Deprecated
	public LivroEntidade() {
	
	}

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
	public LivroEntidade(@NotNull String titulo, @NotNull String resumo, String sumario,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) int numeroPaginas, @NotBlank String isbn,
			LocalDate dataPublicacao, CategoriaEntidade categoria, AutorEntidade autor) {
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

	/**
	 * @return the resumo
	 */
	public String getResumo() {
		return resumo;
	}


	/**
	 * @return the sumario
	 */
	public String getSumario() {
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
	public CategoriaEntidade getCategoria() {
		return categoria;
	}

	/**
	 * @return the autor
	 */
	public AutorEntidade getAutor() {
		return autor;
	}	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LivroEntidade other = (LivroEntidade) obj;
		return Objects.equals(isbn, other.isbn);
	}



	@Override
	public String toString() {
		return "TituloEntidade [id=" + id + ", titulo=" + titulo + ", resumo=" + resumo + ", sumario=" + sumario
				+ ", preco=" + preco + ", numeroPaginas=" + numeroPaginas + ", isbn=" + isbn + ", dataPublicacao="
				+ dataPublicacao + ", categoria=" + categoria + ", autor=" + autor + "]";
	}

	
}
