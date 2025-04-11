package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model;

import java.math.BigDecimal;

import br.com.jornada.dev.primeiro.desafio.negocio.livro.LivroEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.livro.repository.LivroRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.PedidoItemEntidade;
import br.com.jornada.dev.primeiro.desafio.validador.ExistisId;
import jakarta.persistence.NoResultException;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PedidoItemRequest {

	@NotNull
	@ExistisId(domainClass = LivroEntidade.class, fieldName = "id")
	private Long livro;

	@Positive
	private int quantidade;


	public PedidoItemRequest(@NotNull Long idLivro, @Positive int quantidade) {
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
	
	
	/**
	 * Retorna o preco deste item
	 * @param livroRepositorio
	 * @return
	 */
	public BigDecimal getTotal(final LivroRepositorio livroRepositorio) {
		return livroRepositorio.findById(livro).orElseThrow(NoResultException::new)
				.getPreco()
				.multiply(new BigDecimal(quantidade));
	}

	
	public PedidoItemEntidade toEntidade(final LivroRepositorio livroRepositorio) {
		return new PedidoItemEntidade(
				livroRepositorio.findById(livro).orElseThrow(() -> new NoResultException("Livro não cadastrado.")),
				quantidade
		);
	}

	@Override
	public String toString() {
		return "CompraItem [livro=" + livro + ", quantidade=" + quantidade + "]";
	}

}
