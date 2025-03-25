package br.com.jornada.dev.primeiro.desafio.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.jornada.dev.primeiro.desafio.entidade.LivroEntidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


/**
 * <p>
 * Classe que representa a resposta que será enviada ao usuário, ao realizar a busca 
 * de um livro pelo ID
 * </p>

 * 
 * @author rafael.altagnam
 * @desafio 1
 */
public class LivroDetalheResponse {
	
	private Long id;
	private String titulo;	
	private String resumo;
	private String sumario;
	private BigDecimal preco;
	private int numeroPaginas;
	private String isbn;
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataPublicacao;
	private LivroDetalhesAutorResponse autor;
	
	/**
	 * 
	 * @param livro
	 */
	public LivroDetalheResponse(@Valid @NotNull LivroEntidade livro) {
		Assert.notNull(livro, "Um livro deve ser informado.");
		this.id =	livro.getId(); 
		this.titulo =	livro.getTitulo(); 
		this.resumo = livro.getResumo(); 
		this.sumario =		livro.getSumario(); 
		this.preco = livro.getPreco();
		this.numeroPaginas = livro.getNumeroPaginas(); 
		this.isbn = livro.getIsbn(); 
		this.dataPublicacao = livro.getDataPublicacao(); 
		this.autor = new LivroDetalhesAutorResponse(livro.getAutor().getNome(), livro.getAutor().getDescricao());
	}

	/**
	 * Representa a informacao básica do autor do livro, para o contexto de exibição 
	 * de dados do livro
	 * @author rafael.altagnam
	 *
	 */
	public record LivroDetalhesAutorResponse(String nome, String descricao) {};

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
	 * @return the autor
	 */
	public LivroDetalhesAutorResponse getAutor() {
		return autor;
	}


	@Override
	public String toString() {
		return "LivroDadosCompletosResponse [id=" + id + ", titulo=" + titulo + ", resumo=" + resumo + ", sumario="
				+ sumario + ", preco=" + preco + ", numeroPaginas=" + numeroPaginas + ", isbn=" + isbn
				+ ", dataPublicacao=" + dataPublicacao + ", autor=" + autor + "]";
	}
	
}
