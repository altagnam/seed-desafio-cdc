package br.com.jornada.dev.primeiro.desafio.model;

import br.com.jornada.dev.primeiro.desafio.entidade.CompraEntidade;
import br.com.jornada.dev.primeiro.desafio.entidade.CompraItemEntidade;
import br.com.jornada.dev.primeiro.desafio.entidade.LivroEntidade;
import br.com.jornada.dev.primeiro.desafio.repository.LivroRepositorio;
import br.com.jornada.dev.primeiro.desafio.validador.ExistisId;
import jakarta.persistence.NoResultException;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CompraItemRequest {

	@NotNull
	@ExistisId(domainClass = LivroEntidade.class, fieldName = "id")
	private Long livro;

	@Positive
	private int quantidade;


	public CompraItemRequest(@NotNull Long idLivro, @Positive int quantidade) {
		this.livro = idLivro;
		this.quantidade = quantidade;
	}

	/**
	 * @return the livro
	 */	
	public Long getLivro() {
		return livro;
	}

	/**
	 * @return the quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}
	
	
	public CompraItemEntidade toEntidade(final LivroRepositorio livroRepositorio, final CompraEntidade compra) {
		return new CompraItemEntidade(
				livroRepositorio.findById(livro).orElseThrow(() -> new NoResultException("Livro não cadastrado.")),
				quantidade,
				compra
		);
	}

	@Override
	public String toString() {
		return "CompraItem [livro=" + livro + ", quantidade=" + quantidade + "]";
	}

}
